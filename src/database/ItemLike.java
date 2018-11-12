package database;

public class ItemLike {

	private int item_like_id;
	private int item_id;
    private String item_title;
    private int liker_id;
    // active: {yes, no}
    private String active = "yes";
    private String remarks;

    private Item item;

    /** Constructors **/
    public ItemLike() {}

    public ItemLike(int item_id, String item_title, int liker_id, String active, String remarks) {
    	this.item_id = item_id;
    	this.item_title = item_title;
        this.liker_id = liker_id;
        this.active = active;
        this.remarks = remarks;
    }

    /** Getters **/
    public int getItemLikeId() {
        return this.item_like_id;
    }

    public int getItemId() {
        return this.item_id;
    }

    public String getItemTitle() {
        return this.item_title;
    }

    public int getLikerId() {
        return this.liker_id;
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

    /** Setters **/
    public void setItem_id(int item_id) { 
    	this.item_id = item_id; 
    }

    public void setItemTitle(String item_title) {
        this.item_title = item_title;
    }

    public void setLiker_id(int liker_id) { 
    	this.item_id = liker_id; 
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

}
