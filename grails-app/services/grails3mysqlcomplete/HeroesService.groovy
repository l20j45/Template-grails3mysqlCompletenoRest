package grails3mysqlcomplete

import grails.gorm.services.Service

@Service(Heroes)
interface HeroesService {

    Heroes get(Serializable id)

    List<Heroes> list(Map args)

    Long count()

    void delete(Serializable id)

    Heroes save(Heroes heroes)

}