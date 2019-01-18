package bookstoreongrails

class AuthorController {

    AuthorService authorService

    def index() {
        List<Author> authors = authorService.getAllAuthors()
        [
                authors : authors,
                unsuccessfulAuthor : authorService.getUnsuccessfulAuthor(authors),
                mostSuccessfulAuthor : authorService.getMostSuccessfulAuthor(authors)
        ]
    }
}
