package bookstoreongrails

import grails.gorm.transactions.Transactional

@Transactional
class BookService {

    def serviceMethod() {

    }

    List<Book> getAllBooks() {
        Book.list()
    }

}
