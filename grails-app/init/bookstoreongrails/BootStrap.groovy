package bookstoreongrails

class BootStrap {

    def init = { servletContext ->
        Book book1 = new Book()
        Book book2 = new Book()
        Book book3 = new Book()
        Book book4 = new Book()
        Author author1 = new Author()
        Set<Book> bookSet = new HashSet<>()

        book1.countOfCopies = 5000
        book1.ISBN = "001-002-003"
        book1.publishDate = new Date()
        book1.title = "first book from bootstrap"
        book2.countOfCopies = 10000
        book2.ISBN = "002-003-004"
        book2.publishDate = new Date()
        book2.title = "second book"
        book3.countOfCopies = 10000
        book3.ISBN = "002-003-004"
        book3.publishDate = new Date()
        book3.title = "third book"
        book4.countOfCopies = 10000
        book4.ISBN = "002-003-004"
        book4.publishDate = new Date()
        book4.title = "fourth book"

        bookSet.add(book1)
        bookSet.add(book2)

        author1.address = "address"
        author1.authorInfo = "authorInfo"
        author1.birthday = new Date()
        author1.name = "AuthorName"
        author1.books = bookSet
        author1.save()
        book3.save()
        book4.save()
    }
    def destroy = {
        println("Good bye...")
    }
}
