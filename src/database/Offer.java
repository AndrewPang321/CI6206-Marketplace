package database;

public class Offer {

	private int offer_id;
	private int buyer_id;
    private int item_id;
    private String item_title;
    private float offer_price;
    private String offer_status;
    // active: {yes, no}
    private String active = "yes";
    private String remarks;

    private Offer offer;

    /** Constructors **/
    public Offer() {}

    public Offer(int buyer_id, int item_id, String item_title, float offer_price, String offer_status) {
    	this.buyer_id = buyer_id;
        this.item_id = item_id;
        this.item_title = item_title;
        this.offer_price = offer_price;
        this.offer_status = offer_status;
    }

    /** Getters **/
    public int getOfferId() {
        return this.offer_id;
    }
    
    public int getBuyerId() {
        return this.buyer_id;
    }
    
    public int getItemId() {
        return this.item_id;
    }
    
    public String getItemTitle() {
        return this.item_title;
    }

    public float getOfferPrice() {
        return this.offer_price;
    }
    
    public String getOfferStatus() {
        return this.offer_status;
    }

    public String getActive() {
        return this.active;
    }

    public String getRemarks() {
        return this.remarks;
    }

    /** Setters **/
    public void setBuyer_id(int buyer_id) { 
    	this.buyer_id = buyer_id; 
    }
    
    public void setItem_id(int item_id) { 
    	this.item_id = item_id; 
    }

    public void setItemTitle(String item_title) {
        this.item_title = item_title;
    }

    public void setOfferPrice(float offer_price) { 
    	this.offer_price = offer_price; 
    }

    public void setOfferStatus(String offer_status) {
        this.offer_status = offer_status;
    }
}
