package database;

public class Item {

    private int item_id;
    private int user_id;
    private String item_title;
    private String item_category;
    private String item_description;
    private String item_condition;
    private String item_location;
    private String item_delivery_mode;
    private int item_like_count = 0;
    private String item_status = "active";
    private float selling_price;
    private float shipping_fee;
    // active: {yes, no}
    private String active = "yes";
    private String remarks;

    private User user;
    private ItemPhoto itemPhoto;

    /** Constructors **/
    public Item() {}

    public Item(int item_id, int user_id, String item_title, String item_category, String item_description, String item_condition, String item_location, String item_delivery_mode, int item_like_count, String item_status, float selling_price, float shipping_fee, String active, String remarks) {
        this.item_id = item_id;
        this.user_id = user_id;
        this.item_title = item_title;
        this.item_category = item_category;
        this.item_description = item_description;
        this.item_condition = item_condition;
        this.item_location = item_location;
        this.item_delivery_mode = item_delivery_mode;
        this.item_like_count = item_like_count;
        this.item_status = item_status;
        this.selling_price = selling_price;
        this.shipping_fee = shipping_fee;
        this.active = active;
        this.remarks = remarks;
    }

    public Item(int user_id, String item_title, String item_category, String item_description, String item_condition, String item_location, String item_delivery_mode, int item_like_count, String item_status, float selling_price, float shipping_fee, String active, String remarks) {
    	this.user_id = user_id;
    	this.item_title = item_title;
        this.item_category = item_category;
        this.item_description = item_description;
        this.item_condition = item_condition;
        this.item_location = item_location;
        this.item_delivery_mode = item_delivery_mode;
        this.item_like_count = item_like_count;
        this.item_status = item_status;
        this.selling_price = selling_price;
        this.shipping_fee = shipping_fee;
        this.active = active;
        this.remarks = remarks;
    }

    /** Getters **/
    public int getItemId() {
        return this.item_id;
    }

    public int getUserId() {
        return this.user_id;
    }

    public String getItemTitle() {
        return this.item_title;
    }

    public String getItemCategory() {
        return this.item_category;
    }

    public String getItemDescription() {
        return this.item_description;
    }

    public String getItemCondition() {
        return this.item_condition;
    }

    public String getItemLocation() {
        return this.item_location;
    }

    public String getItemDeliveryMode() {
        return this.item_delivery_mode;
    }

    public int getItemLikeCount() {
        return this.item_like_count;
    }

    public String getItemStatus() {
        return this.item_status;
    }
    
    public float getSellingPrice() {
        return this.selling_price;
    }
    
    public float getShippingFee() {
        return this.shipping_fee;
    }

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
    public void setUser_id(int user_id) { 
    	this.user_id = user_id; 
    }

    public void setItemTitle(String item_title) {
        this.item_title = item_title;
    }

    public void setItemCategory(String item_category) {
        this.item_category = item_category;
    }

    public void setItemDescription(String item_description) {
        this.item_description = item_description;
    }

    public void setItemCondition(String item_condition) {
        this.item_condition = item_condition;
    }

    public void setItemLocation(String item_location) {
        this.item_location = item_location;
    }
    
    public void setItemDeliveryMode(String item_delivery_mode) {
        this.item_delivery_mode = item_delivery_mode;
    }

    public void setItemLikeCount(int item_like_count) {
        this.item_like_count = item_like_count;
    }
    
    public void setItemStatus(String item_status) {
        this.item_status = item_status;
    }
    
    public void setSellingPrice(float selling_price) {
        this.selling_price = selling_price;
    }
    
    public void setShippingFee(float shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setItemPhoto(ItemPhoto itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

}
