package Model;

public class Employee {
    private int employeeID;
    private String employeeName;
    private String userName;
    private String passWord;
    private String employeeLoc;
    private String admin;



    /**
     * User object constructor
     * @param employeeID
     * @param userName
     * @param passWord
     */
    public Employee(int employeeID, String employeeName, String userName, String passWord, String employeeLoc, String admin) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.userName = userName;
        this.passWord = passWord;
        this.employeeLoc = employeeLoc;
        this.admin = admin;
    }

    /**
     * Get methods for User object
     * @return
     */
    public int getEmployeeID() { return this.employeeID; }

    public String getUserName() { return this.userName; }

    public String getPassWord() { return this.passWord; }

    public String getEmployeeName() { return employeeName; }

    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getEmployeeLoc() { return employeeLoc; }

    public void setEmployeeLoc(String employeeLoc) { this.employeeLoc = employeeLoc; }

    public String getAdmin() { return admin; }

    public void setAdmin(String admin) { this.admin = admin; }
}


