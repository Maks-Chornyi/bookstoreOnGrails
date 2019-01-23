package bookstoreongrails

import grails.converters.JSON


class AuthorController {

    AuthorService authorService
    BookService bookService
    static allowedMethods = [save: 'POST', deleteAuthor: 'POST', update: 'POST']

    def index() {
        List<Author> authors = authorService.getAllAuthors()
        Author unsuccessfulAuthor = authorService.getUnsuccessfulAuthor(authors)
        Author mostSuccessfulAuthor = authorService.getMostSuccessfulAuthor(authors)
        [
                authors : authors,
                authorsAmount : authors.size(),
                unsuccessfulAuthor : unsuccessfulAuthor,
                mostSuccessfulAuthor : mostSuccessfulAuthor,
                countOfBooksOfUnsuccessfulAuthor : authorService.getCountOfPublishedBookOfAuthor(unsuccessfulAuthor),
                countOfBooksOfMostsuccessfulAuthor : authorService.getCountOfPublishedBookOfAuthor(mostSuccessfulAuthor),
        ]
    }

    def save() {
        authorService.save(params)
        redirect(action: "index")
    }

    def update() {
        authorService.update(params)
        redirect(action: "index")
    }

    def getAuthorInfoById() {
        render authorService.getAuthorInfoById(params) as JSON

    }

    def deleteAuthor() {
        Author author = Author.get(params.id)
        try {
            author.delete(flush: true)
        } catch (Exception e) {
            e.getStackTrace()
        }

        redirect(action: "index")
    }

    def show(int id) {
        Author author = Author.get(id)
        Set<Book> authorsBooks = author.books
        if(authorsBooks.size() > 0) {
            Book mostSuccessfulBook =  bookService.getMostSuccessfulBook(authorsBooks)
            Book unsuccessfulBook = bookService.getUnsuccessfulBook(authorsBooks)
            Book firstAuthorBook = bookService.getFirstBookOfAuthor(authorsBooks)
            Book lastAuthorBook = bookService.getLastBookOfAuthor(authorsBooks)
            return [
                author : author,
                authorsBooks : authorsBooks,
                countOfAuthorsBooks : author.books.size(),
                mostSuccessfulBook : mostSuccessfulBook,
                countOfCopiesOfMostSuccessfulBook : mostSuccessfulBook.countOfCopies,
                unsuccessfulBook : unsuccessfulBook,
                countofCopiesOfUnsuccessfulBook : unsuccessfulBook.countOfCopies,
                firstBookOfAuthor : firstAuthorBook,
                publishDateOfFirstBook : firstAuthorBook.publishDate,
                lastBookOfAuthor : lastAuthorBook,
                publishDateOfLastBook : lastAuthorBook.publishDate
            ]
        } else {
            //if author hasn't books
            return [
                    author : author,
                    authorsBooks : author.books*.id
            ]
        }
    }
}
