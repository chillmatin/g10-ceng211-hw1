public class Issue {
    private String id;
    private String memberId;
    private String bookId;
    private Book book;
    private Date issueDate;
    private Date returningDate;
    private double Penalty;


    /**
     * This constructor takes issue details in a String array format
     * Details are given in the following order:
     * [id, memberId, bookId, issueDate, returningDate]
     * all of which are String values.
     */
    Issue(String[] issueDetails) {

        this.setId(issueDetails[0]);
        this.setMemberId(issueDetails[1]);
        this.setBookId(issueDetails[2]);
        this.setIssueDate(issueDetails[3]);
        this.setReturningDate(issueDetails[4]);
        this.setPenalty(issueDate, returningDate);

        this.setMember(getMemberId());
        this.setBook(getBookId());


    }

    Issue(Issue issue) {
        this.setId(issue.getId());
        this.setMemberId(issue.getMemberId());
        this.setBookId(issue.getBookId());
        this.setIssueDate(issue.getIssueDate());
        this.setReturningDate(issue.getReturningDate());
        this.setMember(issue.getMember());
        this.setBook(issue.getBook());
        this.setPenalty(issueDate, returningDate);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getMemberId() {
        return memberId;
    }

    private void setMemberId(String memberID) {
        this.memberId = memberID;
    }


    public Member getMember() {
        MemberManager memberManager = new MemberManager("Members.csv");
        return memberManager.getMemberById(memberId);
    }

    private void setMember(Member member) {
    }

    private void setMember(String id) {
        MemberManager memberManager = new MemberManager("Members.csv");
        setMember(memberManager.getMemberById(id));
    }


    public String getBookId() {
        return bookId;
    }

    private void setBookId(String bookID) {
        this.bookId = bookID;
    }


    public Book getBook() {
        return book;
    }

    private void setBook(String id) {
        BookManager bookManager = new BookManager("L1_Books.csv", "L2_Books.csv", "L3_Books.csv");
        setBook(bookManager.getBookById(id));

    }

    private void setBook(Book book) {
        this.book = book;
    }


    public Date getIssueDate() {
        return new Date(this.issueDate);
    }

    private void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    private void setIssueDate(String issueDate) {
        this.issueDate = new Date(issueDate);
    }


    public Date getReturningDate() {
        return new Date(this.returningDate);
    }

    private void setReturningDate(Date returningDate) {
        this.returningDate = returningDate;
    }

    private void setReturningDate(String returningDate) {
        this.returningDate = new Date(returningDate);
    }


    public double getPenalty() {
        return Penalty;
    }

    private void setPenalty(double penalty) {
        Penalty = penalty;
    }

    /**
     * issueDate.compareTo(returningDate) should give something between 0 and -14 inclusively
     * if value is less than -14, every day is counted as 0.5 Lira
     *
     * @param issueDate     date when book is issued
     * @param returningDate date when book is returned
     */
    private void setPenalty(Date issueDate, Date returningDate) {
        int dayDifference = issueDate.compareTo(returningDate);
        double penalty = 0;

        if (dayDifference < -14) {
            penalty = (dayDifference + 14) * 0.5;
        }

        setPenalty(penalty);
    }


    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", bookId=" + bookId +
                ", issueDate=" + issueDate +
                ", returningDate=" + returningDate +
                '}';
    }
}
