package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exception.*;

public class DBAO {

    private Connection con;
    private boolean conFree = true;

    // Database configuration
    private static String url = "jdbc:mysql://161.117.121.110:3306/marketplace";
    private static String dbdriver = "com.mysql.jdbc.Driver";
    private static String username = "andrew";
    private static String password = "Password123";

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
    
    public int addItem(int user_id, String item_title, String item_category, String item_description, String item_condition,
    		String item_location, String item_delivery_mode, float selling_price, float shipping_fee) throws GeneralException {
        boolean acquireConnection = false;
        int item_id = -1;
        
        try {
            String sqlStatement = "INSERT INTO t_item(user_id, item_title, item_category, " + 
            		"item_description, item_condition, item_location, item_delivery_mode, " +
            		"selling_price, shipping_fee) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            if (conFree) {
                getConnection();
                acquireConnection = true;
            }

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setInt(1, user_id);
            prepStmt.setString(2, item_title);
            prepStmt.setString(3, item_category);
            prepStmt.setString(4, item_description);
            prepStmt.setString(5, item_condition);
            prepStmt.setString(6, item_location);
            prepStmt.setString(7, item_delivery_mode);
            prepStmt.setFloat(8, selling_price);
            prepStmt.setFloat(9, shipping_fee);
            prepStmt.executeUpdate();

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new GeneralException(ex.getMessage());
        }

        if (acquireConnection) {
            releaseConnection();
        }
        return item_id;
    }

    public ArrayList<Item> getAllItem() throws GeneralException {
        ArrayList<Item> allItems = new ArrayList<Item>();

        try {
            String sqlStatement = "SELECT * FROM t_item";
            getConnection();

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(rs.getInt("item_id"), rs.getInt("user_id"),
                        rs.getString("item_title"), rs.getString("item_category"),
                        rs.getString("item_description"), rs.getString("item_condition"),
                        rs.getString("item_location"), rs.getString("item_delivery_mode"),
                        rs.getInt("item_like_count"), rs.getString("item_status"),
                        rs.getFloat("selling_price"), rs.getFloat("shipping_fee"),
                        rs.getString("active"), rs.getString("remarks"));
//                item.setItemPhoto(getItemPhoto(item.getItemId()));
                allItems.add(item);
            }

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new GeneralException(ex.getMessage());
        }
        releaseConnection();
        return allItems;
    }

    public ItemPhoto getItemPhoto(int item_id) throws GeneralException {
        ItemPhoto itemPhoto = null;
        boolean acquireConnection = false;

        try {
            String sqlStatement = "SELECT * FROM t_item_photo where item_id = ?";
            if (conFree) {
                getConnection();
                acquireConnection = true;
            }

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setInt(1, item_id);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                itemPhoto = new ItemPhoto(rs.getInt("item_id"), rs.getString("photo_name"),
                        rs.getString("photo"), rs.getString("active"),
                        rs.getString("remarks"));
            }

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new GeneralException(ex.getMessage());
        }

        if (acquireConnection) {
            releaseConnection();
        }
        return itemPhoto;
    }
     public ArrayList<Item> getAllItems(int user_id) throws GeneralException {
        ArrayList<Item> allItems = new ArrayList<Item>();

        try {
            String sqlStatement = "SELECT * FROM t_item where t.user_id = ?";
            getConnection();
            

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setInt(1, user_id);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(rs.getInt("item_id"), rs.getInt("user_id"),
                        rs.getString("item_title"), rs.getString("item_category"),
                        rs.getString("item_description"), rs.getString("item_condition"),
                        rs.getString("item_location"), rs.getString("item_delivery_mode"),
                        rs.getInt("item_like_count"), rs.getString("item_status"),
                        rs.getFloat("selling_price"), rs.getFloat("shipping_fee"),
                        rs.getString("active"), rs.getString("remarks"));
//                item.setItemPhoto(getItemPhoto(item.getItemId()));
                allItems.add(item);
            }

            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new GeneralException(ex.getMessage());
        }
        releaseConnection();
        return allItems;
    }
    public User getSeller(int seller_id) throws Exception {
        User user = null;
        try {
            String sqlStatement = "SELECT * FROM t_user WHERE t_user.id = ?";
            getConnection();

            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setInt(1, seller_id);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
            	int user_id = rs.getInt("user_id");
            	ArrayList<Item> item = getAllItems(user_id);
                user = new User(rs.getString("email"), rs.getString("firstname"), rs.getString("lastname")
                				, rs.getString("gender"), rs.getInt("contact")
                    			, rs.getString("country")
                    			, item);
            	
            }
            prepStmt.close();
        } catch (SQLException ex) {
            releaseConnection();
            throw new UserNotFoundException(ex.getMessage());
        }
        releaseConnection();
        return user;
    }
}
