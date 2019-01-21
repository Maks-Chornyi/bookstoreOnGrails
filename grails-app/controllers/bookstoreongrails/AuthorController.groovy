package bookstoreongrails

class AuthorController {

    AuthorService authorService
    static allowedMethods = [save: 'POST']

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

    def deleteAuthor(int id) {
        Author author = Author.get(id)
        author.delete()
        redirect(action: "index")
    }
}
