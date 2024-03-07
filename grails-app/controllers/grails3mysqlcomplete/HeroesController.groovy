package grails3mysqlcomplete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HeroesController {

    HeroesService heroesService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond heroesService.list(params), model: [heroesCount: heroesService.count()]

    }
    def indexJson(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def contentType = request.contentType
        respond {
            // Verificar el tipo de contenido
            if (contentType == "application/json") {
                // Responder con JSON
                respond heroesService.list(params), model: [heroesCount: heroesService.count()],formats: ['json']
            } else {
                // Responder con una vista
                respond heroesService.list(params), model: [heroesCount: heroesService.count()]
            }
        }



    }






def show(Long id) {
    respond heroesService.get(id)
}

def create() {
    respond new Heroes(params)
}

def save(Heroes heroes) {
    if (heroes == null) {
        notFound()
        return
    }

    try {
        heroesService.save(heroes)
    } catch (ValidationException e) {
        respond heroes.errors, view: 'create'
        return
    }

    request.withFormat {
        form multipartForm {
            flash.message = message(code: 'default.created.message', args: [message(code: 'heroes.label', default: 'Heroes'), heroes.id])
            redirect heroes
        }
        '*' { respond heroes, [status: CREATED] }
    }
}

def edit(Long id) {
    respond heroesService.get(id)
}

def update(Heroes heroes) {
    if (heroes == null) {
        notFound()
        return
    }

    try {
        heroesService.save(heroes)
    } catch (ValidationException e) {
        respond heroes.errors, view: 'edit'
        return
    }

    request.withFormat {
        form multipartForm {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'heroes.label', default: 'Heroes'), heroes.id])
            redirect heroes
        }
        '*' { respond heroes, [status: OK] }
    }
}

def delete(Long id) {
    if (id == null) {
        notFound()
        return
    }

    heroesService.delete(id)

    request.withFormat {
        form multipartForm {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'heroes.label', default: 'Heroes'), id])
            redirect action: "index", method: "GET"
        }
        '*' { render status: NO_CONTENT }
    }
}

protected void notFound() {
    request.withFormat {
        form multipartForm {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'heroes.label', default: 'Heroes'), params.id])
            redirect action: "index", method: "GET"
        }
        '*' { render status: NOT_FOUND }
    }
}

}
