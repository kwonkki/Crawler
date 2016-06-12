package com.crawler.cebu;

public class Flight {
	private String carrier;
	private String flightNumber;
	private String depAirport;
	private String arrAirport;
	private String depTime;
	private String arrTime;
	private String adultPrice;
	private String adultTax;
	
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepAirport() {
		return depAirport;
	}
	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}
	public String getArrAirport() {
		return arrAirport;
	}
	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
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
	
	@Override
	public String toString() {
		String info = "carrier=" + this.getCarrier() + ", "
				+ "flightNumber=" + this.getFlightNumber() + ", "
				+ "depAirport=" + this.getDepAirport() + ", "
				+ "arrAirport=" + this.getArrAirport() + ", "
				+ "depTime=" + this.getDepTime() + ", "
				+ "arrTime=" + this.getArrTime() + ", "
				+ "adultPrice=" + this.getAdultPrice() + ", "
				+ "adultTax=" + this.getAdultTax();
		return info;
	}
	
}
