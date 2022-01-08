public class MemberManager {

    private Member[] members;

    MemberManager(String pathName) {
        this.setMembers(getMembersByFilePath(pathName));
    }


    /**
     * This method accesses member list file and extracts all the data to an array
     *
     * @param pathName Path to the file that includes a list of all members
     * @return An array of Member objects
     */
    private Member[] getMembersByFilePath(String pathName) {
        FileIO fileIO = new FileIO(pathName);
        String[][] argumentsArray = fileIO.getSplitLines();
        Member[] members = new Member[argumentsArray.length];

        for (int i = 0; i < argumentsArray.length; i++) {
            members[i] = new Member(argumentsArray[i]);
        }

        return members;

    }

    /**
     * @param members An array of members
     */
    private void setMembers(Member[] members) {
        this.members = members;
    }

    /**
     * @param id id of the member object to be returned
     * @return Member object
     */
    public Member getMemberById(String id) {
        Member match = null;
        for (Member member : members) {
            if (member.getId().equals(id)) {
                match = member;
                break;
            }
        }
        return match;
    }

}
