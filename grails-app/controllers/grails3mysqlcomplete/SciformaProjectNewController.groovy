package grails3mysqlcomplete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SciformaProjectNewController {

    SciformaProjectNewService sciformaProjectNewService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sciformaProjectNewService.list(params), model:[sciformaProjectNewCount: sciformaProjectNewService.count()]
    }

    def indexFilter() {
        def result = SciformaProjectNew.list()

    }

    def test(Integer id, Boolean message) {
        response.setContentType('application/json')
        render  String.format('[{"name":"Afghanistan %s", "code":"AF %s"}, {"name":"Aland Islands", "code":"AX"}, {"name":"Albania", "code":"AL"}]', id, message)
    }
    def show(Long id) {
        respond sciformaProjectNewService.get(id)
    }

    def create() {
        respond new SciformaProjectNew(params)
    }

    def save(SciformaProjectNew sciformaProjectNew) {
        if (sciformaProjectNew == null) {
            notFound()
            return
        }

        try {
            sciformaProjectNewService.save(sciformaProjectNew)
        } catch (ValidationException e) {
            respond sciformaProjectNew.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sciformaProjectNew.label', default: 'SciformaProjectNew'), sciformaProjectNew.id])
                redirect sciformaProjectNew
            }
            '*' { respond sciformaProjectNew, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sciformaProjectNewService.get(id)
    }

    def update(SciformaProjectNew sciformaProjectNew) {
        if (sciformaProjectNew == null) {
            notFound()
            return
        }

        try {
            sciformaProjectNewService.save(sciformaProjectNew)
        } catch (ValidationException e) {
            respond sciformaProjectNew.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sciformaProjectNew.label', default: 'SciformaProjectNew'), sciformaProjectNew.id])
                redirect sciformaProjectNew
            }
            '*' { respond sciformaProjectNew, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sciformaProjectNewService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sciformaProjectNew.label', default: 'SciformaProjectNew'), id])
                redirect action:'index', method:'GET'
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sciformaProjectNew.label', default: 'SciformaProjectNew'), params.id])
                redirect action: 'index', method: 'GET'
            }
            '*' { render status: NOT_FOUND }
        }
    }

}
