package bookstoreongrails

import grails.gorm.transactions.Transactional

@Transactional
class AuthorService {

    def serviceMethod() {

    }

    void save(Map<String, Object> params) {
        new Author(params).save()
    }

    Author getAuthorInfoById(Map<String, Object> params) {
        Author.get(params.id)
    }

    void update(Map<String, Object> params) {

        Author author = Author.get(params.id)
        author.properties = params
        author.save(failOnError: true)
    }

    List<Author> getAllAuthors() {
        Author.list()
    }

    List<Author> sortAuthorsByBirthday(List<Author> authors) {
        Collections.sort(authors, new Comparator<Author>() {
            int compare(Author o1, Author o2) {
                if(o1 == null || o2 == null) {
                    return 0
                }
                o2.getBirthday() <=> o1.getBirthday()
            }
        })
        authors
    }

    Author getYoungestAuthor(List<Author> authors) {
        authors = sortAuthorsByBirthday(authors)
        authors.get(0)
    }

    Author getOldestAuthor(List<Author> authors) {
        authors = sortAuthorsByBirthday(authors)
        authors.get((authors.size()-1))
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
