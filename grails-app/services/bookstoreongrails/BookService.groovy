package bookstoreongrails

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class BookService {

    def serviceMethod() {

    }

    List<Book> getAllBooks() {
        Book.list()
    }

    /**
     * Unsuccessful book means the book which has lowest count of copies
     * @param books
     * @return Book
     */
    Book getUnsuccessfulBook(Set<Book> books) {
        int countOfCopiesOfUnsuccessfulBook = Integer.MAX_VALUE
        if (books.size() > 0) {
            Book unsuccessfulBook = books[0]

            books.each{book ->
                int copiesOfCurrentBook = book.countOfCopies
                if (copiesOfCurrentBook < countOfCopiesOfUnsuccessfulBook) {
                    unsuccessfulBook = book
                    countOfCopiesOfUnsuccessfulBook = copiesOfCurrentBook
                }
            }
            return unsuccessfulBook
        } else {
            throw new RuntimeException()
        }
    }

    /**
     * Most successful means the book which has most count of copies
     * @param books
     * @return Book
     */
    Book getMostSuccessfulBook(Set<Book> books) {
        int countOfCopiesOfMostSuccessfulBook = 0
        if (books.size() > 0 ) {
            Book mostSuccessfulBook = books[0]
            books.each {book ->
                int copiesOfCurrentBook = book.countOfCopies
                if (copiesOfCurrentBook > countOfCopiesOfMostSuccessfulBook) {
                    countOfCopiesOfMostSuccessfulBook = copiesOfCurrentBook
                    mostSuccessfulBook = book
                }
            }
            return mostSuccessfulBook
        } else {
            throw new RuntimeException()
        }
    }

    Set<Book> sortBooksByPublishDate(Set<Book> books) {
        List<Book> bookList = new ArrayList<>(books)
        Collections.sort(bookList, new Comparator<Book>() {
            int compare(Book b1, Book b2) {
                if(b1 == null || b2 == null) {
                    return 0
                }
                b2.publishDate <=> b1.publishDate
            }
        })
        bookList.toSet()
    }

    Book getLastBookOfAuthor(Set<Book> books) {
        sortBooksByPublishDate(books)
        books[0]
    }

    Book getFirstBookOfAuthor(Set<Book> books) {
        sortBooksByPublishDate(books)
        books[books.size() - 1]
    }

    def deleteBookById(GrailsParameterMap grailsParameterMap) {
        Book book = Book.get(grailsParameterMap.id)
        Set<Author> authors = book.authors
        authors.each {Author author ->
            book.removeFromAuthors(author)
        }
        book.delete(flush: true)
    }
}
