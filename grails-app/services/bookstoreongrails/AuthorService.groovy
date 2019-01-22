package bookstoreongrails

import grails.gorm.transactions.Transactional

@Transactional
class AuthorService {

    def serviceMethod() {

    }

    List<Author> getAllAuthors() {
        Author.list()
    }

    Author getMostSuccessfulAuthor(List<Author> authors) {
        Author mostSuccessfulAuthor = authors.get(0)
        int maxBookCount = 0
        int authorsBookCount = 0
        authors.each { author ->
            Set<Book> books = author.books
            books.each { book ->
                authorsBookCount += book.countOfCopies
            }
            if (authorsBookCount > maxBookCount) {
                maxBookCount = authorsBookCount
                mostSuccessfulAuthor = author
            }
            authorsBookCount = 0
        }
        mostSuccessfulAuthor
    }

    Author getUnsuccessfulAuthor(List<Author> authors) {
        Author unsuccessfulAuthor = authors.get(0)
        int minCountOfBook = Integer.MAX_VALUE
        int authorsBookCount = 0
        authors.each {author ->
            Set<Book> books = author.books
            books.each {book ->
                authorsBookCount += book.countOfCopies
            }
            if (authorsBookCount < minCountOfBook) {
                unsuccessfulAuthor = author
                minCountOfBook = authorsBookCount
            }
            authorsBookCount = 0
        }
        unsuccessfulAuthor
    }

    int getCountOfPublishedBookOfAuthor(Author author) {
        int countOfAllBooks = 0
        Set<Book> books = author.books
        books.each {book ->
            countOfAllBooks += book.countOfCopies
        }
        countOfAllBooks
    }
}
