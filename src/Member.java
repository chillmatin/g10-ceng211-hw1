public class Member {
    /**
     * Each member has id, name, and email.
     * Admins are not considered members.
     */

    private String id;
    private String name;
    private String email;



    /**
     * This constructor takes member details in a String array format
     * Details are given in the following order:
     * [id, name, email]
     * all of which are String values.
     */
    Member(String[] memberDetails) {


        this.setId(memberDetails[0]);
        this.setName(memberDetails[1]);
        this.setEmail(memberDetails[2]);
    }

    Member(Member member) {
        this.setId(member.getId());
        this.setName(member.getName());
        this.setEmail(member.getEmail());
    }


    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
