package bookstoreongrails

class BookController {

    BookService bookService

    def index() {
        [books : Book.list()]
    }
}
