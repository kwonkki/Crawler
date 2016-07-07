package cebu.model;

public class Ticket {
	private String cabin = "U";	// "U"
	private String carrier;
	private String flightnumber;
	private String depairport;
	private String arrairport;
	private String deptime;
	private String arrtime;
	private String currency;
	private String adultprice;
	private String adulttax;
	private String createtime;	// 当前时间 精确到秒
	private int seats = 2;	// 2
	
	private String flytime;	// 飞行时间？
	private String childprice;
	private String childtax;
	
	
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getflightNumber() {
		return flightnumber;
	}

	public void setflightNumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public String getdepAirport() {
		return depairport;
	}

	public void setdepAirport(String depairport) {
		this.depairport = depairport;
	}

	public String getdepTime() {
		return deptime;
	}

	public void setdepTime(String deptime) {
		this.deptime = deptime;
	}

	public String getarrAirport() {
		return arrairport;
	}

	public void setarrAirport(String arrairport) {
		this.arrairport = arrairport;
	}

	public String getarrTime() {
		return arrtime;
	}

	public void setarrTime(String arrtime) {
		this.arrtime = arrtime;
	}

	public String getflyTime() {
		return flytime;
	}

	public void setflyTime(String flytime) {
		this.flytime = flytime;
	}

	public String getadultPrice() {
		return adultprice;
	}

	public void setadultPrice(String adultprice) {
		this.adultprice = adultprice;
	}

	public String getadultTax() {
		return adulttax;
	}

	public void setadultTax(String adulttax) {
		this.adulttax = adulttax;
	}

	public String getchildPrice() {
		return childprice;
	}

	public void setchildPrice(String childprice) {
		this.childprice = childprice;
	}

	public String getchildTax() {
		return childtax;
	}

	public void setchildTax(String childtax) {
		this.childtax = childtax;
	}

	//
	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getcreateTime() {
		return createtime;
	}

	public void setcreateTime(String createtime) {
		this.createtime = createtime;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		String info = 
				"cabin=" + this.getCabin() +
				", carrier=" + this.getCarrier() +
				", flightnumber=" + this.getflightNumber() +
				", depairport=" + this.getdepAirport() +
				", arrairport=" + this.getarrAirport() +
				", deptime=" + this.getdepTime() +
				", arrtime=" + this.getarrTime() +
				", createtime=" + this.getcreateTime() +
				", adultPrice=" + this.getadultPrice() +
				", currency=" + this.getCurrency() + 
				", audltTax=" + this.getadultTax() +
				", seats=" + this.getSeats();
		return info;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
