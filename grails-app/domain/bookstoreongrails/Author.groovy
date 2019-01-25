package bookstoreongrails

class Author {

    static hasMany = [books:Book]
    //static mapping = {books cascade: 'none'}

    String name
    Date birthday
    String address
    String authorInfo

    static constraints = {
        name()
        birthday()
        address()
        authorInfo()
        name size: 2..255, blank: false
        address size: 2..255, blank: false
        birthday blank: false
        authorInfo size: 2..1024, blank: false
    }
}
