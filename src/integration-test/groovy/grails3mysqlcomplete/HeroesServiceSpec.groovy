package grails3mysqlcomplete

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HeroesServiceSpec extends Specification {

    HeroesService heroesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Heroes(...).save(flush: true, failOnError: true)
        //new Heroes(...).save(flush: true, failOnError: true)
        //Heroes heroes = new Heroes(...).save(flush: true, failOnError: true)
        //new Heroes(...).save(flush: true, failOnError: true)
        //new Heroes(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //heroes.id
    }

    void "test get"() {
        setupData()

        expect:
        heroesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Heroes> heroesList = heroesService.list(max: 2, offset: 2)

        then:
        heroesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        heroesService.count() == 5
    }

    void "test delete"() {
        Long heroesId = setupData()

        expect:
        heroesService.count() == 5

        when:
        heroesService.delete(heroesId)
        sessionFactory.currentSession.flush()

        then:
        heroesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Heroes heroes = new Heroes()
        heroesService.save(heroes)

        then:
        heroes.id != null
    }
}
