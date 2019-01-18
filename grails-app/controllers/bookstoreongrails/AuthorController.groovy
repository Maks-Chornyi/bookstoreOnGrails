package bookstoreongrails

class AuthorController {

    AuthorService authorService
    //static allowedMethods = [save: 'POST']

    def index() {
        List<Author> authors = authorService.getAllAuthors()
        [
                authors : authors,
                unsuccessfulAuthor : authorService.getUnsuccessfulAuthor(authors),
                mostSuccessfulAuthor : authorService.getMostSuccessfulAuthor(authors)
        ]
    }

    def save() {
        def author = new Author(params)
        author.save()
        redirect(action: "index")
    }
}
