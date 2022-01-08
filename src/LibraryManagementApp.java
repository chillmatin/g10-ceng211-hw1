public class LibraryManagementApp {
    public static void main(String[] args) {

        LibraryManagement libMan = new LibraryManagement("L1_Issues.csv", "L2_Issues.csv", "L3_Issues.csv");
        LibraryQuery query = new LibraryQuery(libMan);


        System.out.println("1) " + query.getMostIssuedBook());
        System.out.println("2) " + query.getMemberWithMostIssues());
        System.out.println("3) " + query.getHighestPenalty());
        System.out.println("4) " + query.getBookWithMostCopies());
        System.out.println("5) " + query.getPreviouslyIssuedBookWithLeastCopies());
        System.out.println("6) " + query.getMemberWithLeastNumberOfBooks(3));



    }
}
