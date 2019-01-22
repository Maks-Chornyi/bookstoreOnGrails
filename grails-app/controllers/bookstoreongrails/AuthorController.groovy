package bookstoreongrails

import org.springframework.web.bind.annotation.PathVariable

class AuthorController {

    AuthorService authorService
    static allowedMethods = [save: 'POST', deleteAuthor: 'POST']

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
        def author = new Author(params)
        author.save()
        redirect(action: "index")
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
        [author : Author.get(id)]
    }
}
