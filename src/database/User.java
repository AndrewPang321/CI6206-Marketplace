package database;

import java.util.Date;

public class User {

    public static User currentUser = null;

    private int user_id;
    private String email;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String gender;
    private int contact;
    private String address;
    private int postalCode;
    private String country;
    // active: {yes, no}
    private String active = "yes";
    private String remarks;

    private UserAccount userAccount;

    /** Constructors **/
    public User() {}

    public User(String email, String firstname, String lastname, Date dateOfBirth, String gender, int contact, String address, int postalCode, String country, UserAccount userAccount) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.userAccount = userAccount;
        this.userAccount.setUser(this);
    }

    public User(String email, String firstname, String lastname, Date dateOfBirth, String gender, int contact, String address, int postalCode, String country, String active, String remarks, UserAccount userAccount) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.active = active;
        this.remarks = remarks;
        this.userAccount = userAccount;
        this.userAccount.setUser(this);
    }

    /** Getters **/
    public int getUserId() {
        return this.user_id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getGender() {
        return this.gender;
    }

    public int getContact() {
        return this.contact;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPostalCode() {
        return this.postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public String getActive() {
        return this.active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    /** Setters **/
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

}
