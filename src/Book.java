public class Book {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String edition;
    private String genre;
    private int quantity;


    /**
     * This constructor takes book details in a String array format
     * Details are given in the following order:
     * [ID,Title,Author,Publisher,Edition,Genre,Quantity]
     * all of which are String values.
     */
    Book(String[] bookDetails) {

        this.setId(bookDetails[0]);
        this.setTitle(bookDetails[1]);
        this.setAuthor(bookDetails[2]);
        this.setPublisher(bookDetails[3]);
        this.setEdition(bookDetails[4]);
        this.setGenre(bookDetails[5]);
        this.setQuantity(bookDetails[6]);

    }

    /**
     * Creating a book object by copying another book object's values
     *
     * @param book : a non-null book object
     */
    Book(Book book) {

        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setPublisher(book.getPublisher());
        this.setEdition(book.getEdition());
        this.setGenre(book.getGenre());
        this.setQuantity(book.getQuantity());

    }


    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    private void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    private void setEdition(String edition) {
        this.edition = edition;
    }

    public String getGenre() {
        return genre;
    }

    private void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private void setQuantity(String quantity) {
        try {
            this.quantity = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            System.out.println("Not an integer!");
        }

    }

    public boolean equals(Book book) {
        return book.getId().equals(id);  // This method assumes each id is unique to one item
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", edition='" + edition + '\'' +
                ", genre='" + genre + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
