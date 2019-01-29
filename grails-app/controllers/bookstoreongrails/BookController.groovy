package bookstoreongrails

import grails.plugins.redis.RedisService

class BookController {

    BookService bookService
    RedisService redisService

    def index() {
        [books : Book.list()]
    }

    def addNewBook() {
        bookService.addNewBook(params)
        redirect(action: "index")
    }

    def deleteBook() {
        bookService.deleteBookById(params)
        redirect(action: "index")
    }
}
