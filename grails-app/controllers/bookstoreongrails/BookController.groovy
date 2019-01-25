package bookstoreongrails

class BookController {

    BookService bookService

    def index() {
        [books : Book.list()]
    }

    def addNewBook() {
        new Book(params).save()
        redirect(action: "index")
    }

    def deleteBook() {
        bookService.deleteBookById(params)
        redirect(action: "index")
    }
}
