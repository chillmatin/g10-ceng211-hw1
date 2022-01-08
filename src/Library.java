import java.util.Arrays;

public class Library {
    private Book[] books;

    /**
     * Creating a Library object by giving an array of books as an argument
     *
     * @param books
     */
    Library(Book[] books) {
        this.books = books;
    }

    /**
     * Creating a Library object by creating a deep copy of another Library object
     *
     * @param library
     */
    Library(Library library) {
        this.books = library.getBooks();
    }

    /**
     * Creating a Library object using an array of strings
     * with comma separated details about each book
     *
     * @param books
     */
    Library(String[] books) {
        this.books = new Book[books.length];

        for (int i = 0; i < books.length; i++) {
            this.books[i] = new Book(books[i].split(","));

        }
    }

    Library(String pathName) {
        setBooks(pathName);
    }


    public Book[] getBooks() {
        Book[] copiedBooks = new Book[books.length];
        System.arraycopy(books, 0, copiedBooks, 0, books.length);
        return copiedBooks;
    }

    private void setBooks(Book[] books) {
        this.books = books;
    }

    private void setBooks(String pathname) {
        FileIO fileIO = new FileIO(pathname);
        String[][] argumentsList = fileIO.getSplitLines();
        Book[] books = new Book[argumentsList.length];

        for (int i = 0; i < argumentsList.length; i++) {
            books[i] = new Book(argumentsList[i]);
        }

        setBooks(books);

    }

    public int getSize() {
        return this.books.length;
    }


    @Override
    public String toString() {
        return "Library{\n" +
                "books=" + Arrays.toString(books) +
                '}';
    }


}