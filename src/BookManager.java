public class BookManager {

    private Book[] books;
    private Book bookWithMostCopies;


    BookManager(String... pathNames) {
        this.setBooks(getBooksByFilePaths(pathNames));
        setBookWithMostCopies(books);
    }

    BookManager(Book[] books) {
        setBooks(books);
        setBookWithMostCopies(books);
    }

    /**
     * This method accesses Libraries' book list files and extracts all the book data to an array of books
     *
     * @param pathNames Paths to the files that include lists of all books
     * @return An array of Book objects
     */
    private Book[] getBooksByFilePaths(String... pathNames) {
        String rawText = FileIO.concatTextsInFiles(pathNames);
        String[] rawTextLines = rawText.split("\n");

        int parametersPerLine = rawTextLines[0].split(",").length;
        String[][] argumentsArray = new String[rawTextLines.length][parametersPerLine];
        for (int i = 0; i < argumentsArray.length; i++) {
            argumentsArray[i] = rawTextLines[i].split(",");
        }

        Book[] books = new Book[argumentsArray.length];

        for (int i = 0; i < argumentsArray.length; i++) {
            books[i] = new Book(argumentsArray[i]);
        }

        return books;

    }

    private void setBooks(Book[] books) {
        this.books = books;
    }

    public Book getBookById(String id) {
        Book match = null;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                match = book;
                break;
            }
        }
        return match;
    }

    public Book getBookWithMostCopies() {
        return bookWithMostCopies;
    }

    private void setBookWithMostCopies(Book bookWithMostCopies) {
        this.bookWithMostCopies = bookWithMostCopies;
    }

    private void setBookWithMostCopies(Book[] books) {
        Book bookWithMostCopies = books[0];

        for (Book book : books) {
            if (book.getQuantity() > bookWithMostCopies.getQuantity()) {
                bookWithMostCopies = book;
            }
        }

        setBookWithMostCopies(bookWithMostCopies);

    }
}
