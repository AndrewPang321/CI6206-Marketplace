package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exception.*;

public class DBAO {

    private Connection con;
    private boolean conFree = true;

    // Database configuration
    private static String url = "jdbc:mysql://127.0.0.1:3306/marketplace";
    private static String dbdriver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "";

    public DBAO() throws Exception {
        try {
            Class.forName(dbdriver);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("Exception in DBAO: " + ex);
            throw new Exception("Couldn't open connection to database: " + ex.getMessage());
        }
    }

    public void remove() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected synchronized Connection getConnection() {
        while (!conFree) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        conFree = false;
        notify();

        return con;
    }

    protected synchronized void releaseConnection() {
        while (conFree) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        conFree = true;
        notify();
    }

    public User getUser(String email) throws UserNotFoundException {
        User user = null;

        try {
            String sqlStatement = "SELECT * FROM t_user WHERE t_user.email = ?";
            getConnection();

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setString(1, email);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                int user_id = rs.getInt("user_id");
                UserAccount userAccount = getUserAccount(user_id);

                // Convert date string to date object
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirthWithTypeDate = null;
                try {
                    dateOfBirthWithTypeDate = dateFormatter.parse(rs.getString("date_of_birth"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                user = new User(rs.getString("email"), rs.getString("firstname"), rs.getString("lastname")
                        , dateOfBirthWithTypeDate, rs.getString("gender"), rs.getInt("contact")
                        , rs.getString("address"), rs.getInt("postal_code"), rs.getString("country")
                        , userAccount);
                user.setUser_id(user_id);
            }

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new UserNotFoundException(ex.getMessage());
        }
        releaseConnection();
        return user;
    }

    public UserAccount getUserAccount(int user_id) throws UserNotFoundException {
        UserAccount userAccount = null;
        boolean acquireConnection = false;

        try {
            String sqlStatement = "SELECT * FROM t_user_account WHERE t_user_account.user_id = ?";
            if (conFree) {
                getConnection();
                acquireConnection = true;
            }

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setInt(1, user_id);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                userAccount = new UserAccount(rs.getString("username"), rs.getString("password"));
            }

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new UserNotFoundException(ex.getMessage());
        }

        if (acquireConnection) {
            releaseConnection();
        }
        return userAccount;
    }

    public int addUser(String email, String firstname, String lastname, String dateOfBirth, String gender,
                        String contact, String address, String country, String postal_code) throws SignUpException {
        int user_id = -1;

        try {
            String sqlStatement = "INSERT INTO t_user(email, firstname, lastname, date_of_birth, gender, contact, " +
                    "address, country, postal_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            getConnection();

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setString(1, email);
            prepStmt.setString(2, firstname);
            prepStmt.setString(3, lastname);
            prepStmt.setString(4, dateOfBirth);
            prepStmt.setString(5, gender);
            prepStmt.setString(6, contact);
            prepStmt.setString(7, address);
            prepStmt.setString(8, country);
            prepStmt.setString(9, postal_code);
            prepStmt.executeUpdate();

            String getIdStatement = "SELECT * FROM t_user WHERE t_user.email = ?";
            prepStmt = con.prepareStatement(getIdStatement);
            prepStmt.setString(1, email);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                user_id = rs.getInt("user_id");
            }

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new SignUpException(ex.getMessage());
        }
        releaseConnection();
        return user_id;
    }

    public void addUserAccount(int user_id, String username, String password) throws SignUpException {
        boolean acquireConnection = false;

        try {
            String sqlStatement = "INSERT INTO t_user_account(user_id, username, password) VALUES (?, ?, ?)";
            if (conFree) {
                getConnection();
                acquireConnection = true;
            }

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setInt(1, user_id);
            prepStmt.setString(2, username);
            prepStmt.setString(3, password);
            prepStmt.executeUpdate();

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new SignUpException(ex.getMessage());
        }

        if (acquireConnection) {
            releaseConnection();
        }
    }

}
