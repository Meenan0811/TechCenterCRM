package Model;

public class Employee {
    private int employeeID;
    private String employeeName;
    private String userName;
    private String passWord;



    /**
     * User object constructor
     * @param employeeID
     * @param userName
     * @param passWord
     */
    public Employee(int employeeID, String employeeName, String userName, String passWord) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * Get methods for User object
     * @return
     */
    public int getUserId() { return this.employeeID; }

    public String getUserName() { return this.userName; }

    public String getPassWord() { return this.passWord; }

    public String getEmployeeName() { return employeeName; }

    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
}


