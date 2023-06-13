package Model;

public class Employee {
    private int userId;
    private String userName;
    private String passWord;

    /**
     * User object constructor
     * @param userId
     * @param userName
     * @param passWord
     */
    public Employee(int userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * Get methods for User object
     * @return
     */
    public int getUserId() { return this.userId; }

    public String getUserName() { return this.userName; }

    public String getPassWord() { return this.passWord; }
}


