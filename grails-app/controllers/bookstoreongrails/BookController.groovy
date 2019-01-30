package bookstoreongrails

class BookController {

    BookService bookService

    def index() {
        [books : bookService.getAllBooks()]
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
