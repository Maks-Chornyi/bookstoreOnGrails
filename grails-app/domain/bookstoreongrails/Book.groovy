package bookstoreongrails

class Book {

    static belongsTo = [Author]
    static hasMany = [authors:Author]

    String ISBN
    String title
    Date publishDate
    int countOfCopies

    static constraints = {
        ISBN()
        title()
        publishDate()
        countOfCopies()
    }
}
