package database;

public class ItemPhoto {

	private int item_photo_id;
	private int item_id;
    private String photo_name;
    private String photo;
    // active: {yes, no}
    private String active = "yes";
    private String remarks;
    private byte[] photo_data;

    private Item item;

    /** Constructors **/
    public ItemPhoto() {}

    public ItemPhoto(int item_id, String photo_name, String photo, String active, String remarks) {
    	this.item_id = item_id;
    	this.photo_name = photo_name;
        this.photo = photo;
        this.active = active;
        this.remarks = remarks;
    }

    /** Getters **/
    public int getItemPhotoId() {
        return this.item_photo_id;
    }

    public int getItemId() {
        return this.item_id;
    }

    public String getPhotoName() {
        return this.photo_name;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getActive() {
        return this.active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Item getItem() {
        return this.item;
    }

    public byte[] getPhotoData() { return photo_data; }

    /** Setters **/
    public void setItem_id(int item_id) { 
    	this.item_id = item_id; 
    }

    public void setPhotoName(String photo_name) {
        this.photo_name = photo_name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setPhotoData(byte[] photo_data) { this.photo_data = photo_data; }

}
