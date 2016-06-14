package cebu.model;

/**
 * 价格类
 * @author Administrator
 *
 */
public class TicketPrice {
	
	private String currency;	// 货币单位
	private int adultPrice;		// 成人价格
	private int adultTax;		// 成人税
	
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
