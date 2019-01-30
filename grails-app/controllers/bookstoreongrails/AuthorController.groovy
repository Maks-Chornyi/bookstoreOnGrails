package bookstoreongrails

import grails.converters.JSON


class AuthorController {

    static allowedMethods = [save: 'POST', deleteAuthor: 'POST', update: 'POST']

    AuthorService authorService
    BookService bookService

    def index() {
        List<Author> authors = authorService.getAllAuthors()
        if(authors.size() > 0) {
            Author unsuccessfulAuthor = authorService.getUnsuccessfulAuthor(authors)
            Author mostSuccessfulAuthor = authorService.getMostSuccessfulAuthor(authors)
            [
                    authors : authors,
                    authorsAmount : authors.size(),
                    unsuccessfulAuthor : unsuccessfulAuthor,
                    mostSuccessfulAuthor : mostSuccessfulAuthor,
                    countOfBooksOfUnsuccessfulAuthor : authorService.getCountOfPublishedBookOfAuthor(unsuccessfulAuthor),
                    countOfBooksOfMostsuccessfulAuthor : authorService.getCountOfPublishedBookOfAuthor(mostSuccessfulAuthor),
                    youngestAuthor : authorService.getYoungestAuthor(authors)
            ]
        } else {
            [authors : authors]
        }
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
        authorService.deleteAuthor(params)


        redirect(action: "index")
    }

    def show(int id) {
        def modelMap
        Author author = Author.get(id)
        Set<Book> authorsBooks = author.books
        if(authorsBooks.size() > 0) {
            Book mostSuccessfulBook =  bookService.getMostSuccessfulBook(authorsBooks)
            Book unsuccessfulBook = bookService.getUnsuccessfulBook(authorsBooks)
            Book firstAuthorBook = bookService.getFirstBookOfAuthor(authorsBooks)
            Book lastAuthorBook = bookService.getLastBookOfAuthor(authorsBooks)
            modelMap =  [
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
            modelMap =  [
                    author : author,
                    authorsBooks : author.books*.id
            ]
        }
        modelMap
    }
}
