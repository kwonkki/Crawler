package cebu.model;

public class TicketPrice {
	private String currency;
	private int adultPrice;
	private int adultTax;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(int adultPrice) {
		this.adultPrice = adultPrice;
	}
	public int getAdultTax() {
		return adultTax;
	}
	public void setAdultTax(int adultTax) {
		this.adultTax = adultTax;
	}
	
	
}
