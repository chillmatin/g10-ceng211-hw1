import java.util.Arrays;

public class LibraryManagement {
    private Issue[][] issues = new Issue[3][];  // There are 3 libraries
    private Book[] issuedBooks;
    private Book mostIssuedBook;
    private Book bookWithFewestCopies;
    private double mostPenalty;
    private Member memberWithMostIssuedBooks;

    private final MemberManager memberManager = new MemberManager("Members.csv");





    /**
     * This Constructor takes path strings as parameters and creates 2D Issue[3][] array in which
     * 1st dimension represent Library, and the 2nd dimension represent the Issue arrays
     *
     * @param libraryPaths : String filename variables.
     */
    LibraryManagement(String... libraryPaths) {
        setIssues(libraryPaths);
        setMostPenalty(issues);
        setIssuedBooks(issues);
        setBookWithFewestCopies(issuedBooks);
        setMostIssuedBook(issuedBooks);
        setMemberWithMostIssuedBooks(issues);


    }

    /**
     * This method creates a deep copy of the issues array
     * and returns copied value back safely
     *
     * @return : Issue[][]
     */
    public Issue[][] getIssues() {

        Issue[][] copiedIssues = new Issue[3][];

        for (int i = 0; i < issues.length; i++) {

            copiedIssues[i] = new Issue[issues[i].length];
            for (int j = 0; j < issues[i].length; j++) {
                copiedIssues[i][j] = new Issue(issues[i][j]);
            }
        }

        return copiedIssues;
    }


    /**
     * Formats library issue files into an array of Issue constructor parameters
     * Each parameter is fed into Issue() constructor and placed in 2D array respectively
     *
     * @param pathNames : [L1_Issues.csv, L2_Issues.csv, L3_Issues.csv] accordingly
     */
    private void setIssues(String[] pathNames) {
        int libraryNumber = 0;
        FileIO fileIO = new FileIO(pathNames[libraryNumber]);
        String[][] argumentsArray = fileIO.getSplitLines();  // argumentsArray is an array of arguments
        // to instantiate Issue object

        Issue[][] issues = new Issue[3][];      // first dimension indices indicate library number

        issues[libraryNumber] = new Issue[argumentsArray.length]; // size of each library's issue array is defined
        // according to the number of issues per library


        // 2D Array of Issues is defined with accordance to argumentsArray
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < argumentsArray.length; i++) {
                issues[libraryNumber][i] = new Issue(argumentsArray[i]);
            }
            if (libraryNumber < 2) {
                libraryNumber++;
                fileIO.setPathName(pathNames[libraryNumber]);
                argumentsArray = fileIO.getSplitLines();
                issues[libraryNumber] = new Issue[argumentsArray.length];
            }
        }

        this.issues = issues;

    }


    public int getSize(int libraryNumber) {
        return this.getIssues()[libraryNumber - 1].length;
    }


    public double getMostPenalty() {
        return mostPenalty;
    }

    private void setMostPenalty(double mostPenalty) {
        this.mostPenalty = mostPenalty;
    }

    /**
     * This method processes each issue and extracts penalty data from each issue
     * and returns the biggest value among them
     *
     * @param issues 2D array of issues
     */
    private void setMostPenalty(Issue[][] issues) {
        double[] penalties = new double[getSize(1) + getSize(2) + getSize(3)];

        int penaltyIndex = 0;
        for (Issue[] issue : issues) {
            for (Issue value : issue) {
                penalties[penaltyIndex] = value.getPenalty();
                penaltyIndex++;
            }
        }

        // TODO implement a minimum value method here, this one is inefficient
        Arrays.sort(penalties);
        setMostPenalty(penalties[0] * -1);

    }

    private void setIssuedBooks(Book[] booksIssued) {
        this.issuedBooks = booksIssued;
    }

    private void setIssuedBooks(Issue[][] issues) {
        Book[] booksIssued = new Book[getSize(1) + getSize(2) + getSize(3)];
        int issuedBooksIndex = 0;
        for (Issue[] issue : issues) {
            for (Issue value : issue) {
                booksIssued[issuedBooksIndex] = value.getBook();
                issuedBooksIndex++;
            }
        }
        setIssuedBooks(booksIssued);
    }


    public Book getBookWithFewestCopies() {
        return bookWithFewestCopies;
    }

    private void setBookWithFewestCopies(Book bookWithFewestCopies) {
        this.bookWithFewestCopies = bookWithFewestCopies;
    }

    private void setBookWithFewestCopies(Book[] books) {
        Book bookWithFewestCopies = books[0];

        for (Book book : books) {
            if (book.getQuantity() < bookWithFewestCopies.getQuantity()) {
                bookWithFewestCopies = book;
            }
        }
        setBookWithFewestCopies(bookWithFewestCopies);
    }


    public Book getMostIssuedBook() {
        return mostIssuedBook;
    }

    private void setMostIssuedBook(Book mostIssuedBook) {
        this.mostIssuedBook = mostIssuedBook;
    }

    private void setMostIssuedBook(Book[] booksIssued) {
        Book mostIssuedBook;

        String[] issuedBookIds = new String[booksIssued.length];
        for (int i = 0; i < booksIssued.length; i++) {
            issuedBookIds[i] = booksIssued[i].getId();
        }

        Arrays.sort(issuedBookIds);

        String mostIssuedBookId = "";

        String firstOccurrenceOfId = issuedBookIds[0];
        int occurrenceCounter = 1;
        int maxOccurrence = 1;

        for (String issuedBookId : issuedBookIds) {
            if (!issuedBookId.equals(firstOccurrenceOfId)) {
                if (occurrenceCounter > maxOccurrence) {
                    maxOccurrence = occurrenceCounter;
                    mostIssuedBookId = firstOccurrenceOfId;
                }
                firstOccurrenceOfId = issuedBookId;
                occurrenceCounter = 1;

            } else {
                occurrenceCounter++;
            }
        }

        BookManager bookManager = new BookManager(booksIssued);
        mostIssuedBook = bookManager.getBookById(mostIssuedBookId);

        setMostIssuedBook(mostIssuedBook);


    }


    public Member getMemberWithMostIssuedBooks() {
        return new Member(memberWithMostIssuedBooks);
    }

    private void setMemberWithMostIssuedBooks(Member memberWithMostIssuedBooks) {
        this.memberWithMostIssuedBooks = memberWithMostIssuedBooks;
    }

    private void setMemberWithMostIssuedBooks(Issue[][] issues) {
        int[] memberIdOccurrences = new int[getSize(1) + getSize(2)+ getSize(3)];
        int occurrenceIndex = 0;
        for (Issue[] issue : issues) {
            for (Issue value : issue) {
                memberIdOccurrences[occurrenceIndex] = Integer.parseInt(value.getMemberId());
                occurrenceIndex++;
            }
        }
        Arrays.sort(memberIdOccurrences);   // memberId occurrences in int array in a sorted way

        int maxId = 0;

        int firstOccurrenceId = memberIdOccurrences[0];
        int occurrenceCounter = 1;
        int maxOccurrence = 1;

        for (int memberIdOccurrence : memberIdOccurrences) {
            if (memberIdOccurrence != firstOccurrenceId) {
                if (occurrenceCounter > maxOccurrence) {
                    maxOccurrence = occurrenceCounter;
                    maxId = firstOccurrenceId;
                }
                firstOccurrenceId = memberIdOccurrence;
                occurrenceCounter = 1;

            } else {
                occurrenceCounter++;
            }
        }

        setMemberWithMostIssuedBooks(memberManager.getMemberById(String.valueOf(maxId)));

    }


    public Member getMemberWithLeastIssuedBooksFromNthLib(int n) {
        Issue[] issues = this.issues[n-1];

        int[] memberIdOccurrences = new int[issues.length];
        for (int i = 0; i < issues.length; i++){
            memberIdOccurrences[i] = Integer.parseInt(issues[i].getMemberId());
        }

        Arrays.sort(memberIdOccurrences);

        int minId = 0;

        int firstOccurrenceId = memberIdOccurrences[0];
        int occurrenceCounter = 1;
        int minOccurrence = Integer.MAX_VALUE;


        for (int memberIdOccurrence : memberIdOccurrences) {
            if (memberIdOccurrence != firstOccurrenceId) {
                if (occurrenceCounter < minOccurrence) {
                    minOccurrence = occurrenceCounter;
                    minId = firstOccurrenceId;
                }
                firstOccurrenceId = memberIdOccurrence;
                occurrenceCounter = 1;

            } else {
                occurrenceCounter++;
            }
        }

        return memberManager.getMemberById(String.valueOf(minId));

    }

    public String toString() {
        return "LibraryManagement{" +
                "issues=" + Arrays.deepToString(issues) +
                '}';
    }


}
