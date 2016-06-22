package cebu.model;

/**
 * 价格类
 * @author Administrator
 *
 */
public class TicketPrice {
	
	private String currency;	// 货币单位
	private String adultPrice;		// 成人价格
	private String adultTax;		// 成人税
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(String adultPrice) {
		this.adultPrice = adultPrice;
	}
	public String getAdultTax() {
		return adultTax;
	}
	public void setAdultTax(String adultTax) {
		this.adultTax = adultTax;
	}
	
	public String toString() {
		return "currency: " + this.getCurrency() + ", adultPrice: " + this.getAdultPrice() +
				", adultTax: " + this.getAdultTax();
	}
	
}
