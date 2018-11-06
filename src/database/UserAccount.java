package database;

public class UserAccount {

    private String role = "normal user";
    private String username;
    private String password;
    // active: {yes, no}
    private String active = "yes";
    private String remarks;

    private User user;

    /** Constructors **/
    public UserAccount() {}

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAccount(String username, String password, String active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public UserAccount(String username, String password, String active, String remarks) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.remarks = remarks;
    }

    /** Getters **/
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() { return this.role; }

    public String getActive() {
        return this.active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public User getUser() {
        return this.user;
    }

    /** Setters **/
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) { this.role = role; }

    public void setActive(String active) {
        this.active = active;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
