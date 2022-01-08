public class LibraryQuery {

    LibraryManagement libMan;

    LibraryQuery(LibraryManagement libMan) {
        this.libMan = libMan;
    }


    public String getMostIssuedBook() {
        return libMan.getMostIssuedBook().getTitle();
    }

    public String getMemberWithMostIssues(){
        return libMan.getMemberWithMostIssuedBooks().getName();
    }


    public String getHighestPenalty() {
        return libMan.getMostPenalty() + " TL";
    }

    public String getBookWithMostCopies() {
        BookManager bookManager = new BookManager("L1_Books.csv", "L2_Books.csv", "L3_Books.csv");
        return bookManager.getBookWithMostCopies().getTitle();

    }

    public String getPreviouslyIssuedBookWithLeastCopies() {
        return libMan.getBookWithFewestCopies().getTitle();
    }

    public String getMemberWithLeastNumberOfBooks(int n){
        return libMan.getMemberWithLeastIssuedBooksFromNthLib(n).getName();
    }


}
