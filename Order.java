import java.util.List;


public class Order {
    private List<String> contentBarcodesUsed;
    @Override
	public String toString() {
		return "Order [contentBarcodesUsed=" + contentBarcodesUsed + ", price=" + price + ", volume=" + volume
				+ ", contentType=" + contentType + "]";
	}

	private Double price;
    private Integer volume;
    private ContentType contentType;

    /**
     * Constructor to build an order
     * @param contentBarcodesUsed used to identify what content was used to fulfil the order
     * @param price total price for the order
     * @param volume
     * @param contentType
     */
    public Order(List<String> contentBarcodesUsed, Double price, Integer volume, ContentType contentType) {
        this.contentBarcodesUsed = contentBarcodesUsed;
        this.price = price;
        this.volume = volume;
        this.contentType = contentType;
    }

    public List<String> getContentBarcodesUsed() {
        return contentBarcodesUsed;
    }

    public void setContentBarcodesUsed(List<String> contentBarcodesUsed) {
        this.contentBarcodesUsed = contentBarcodesUsed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
