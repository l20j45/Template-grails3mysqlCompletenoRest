package grails3mysqlcomplete

class Heroes {

    String heroes_id, superhero, publisher, alter_ego , first_appearance, characters ,description


    static constraints = {
        superhero maxSize: 17
        publisher maxSize: 13
        alter_ego maxSize: 23
        first_appearance maxSize: 25
        characters maxSize: 89
    }

    static mapping = {
        version(false)
    }
}
