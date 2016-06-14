package com.multithread.test;

public class Ticket {
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
	  
	  public int getadultPrice() {
		return adultprice;
	  }
	  public void setadultPrice(int adultprice) {
		this.adultprice = adultprice;
	  }
	  
	  public int getadultTax() {
	    return adulttax;
	  }
	  public void setadultTax(int adulttax) {
		this.adulttax = adulttax;
	  }
		  
	  public int getchildPrice() {
		return childprice;
	  }
	  public void setchildPrice(int childprice) {
		this.childprice = childprice;
	  }
			  
	  public int getchildTax() {
		return childtax;
	  }
	  public void setchildTax(int childtax) {
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
		  
	  private String cabin;
	  
	  private String carrier;
	  // 
	  private String flightnumber;
	  // 
	  private String depairport;
	  // 
	  private String deptime;
	  
	  private String arrairport;
	  
	  private String arrtime;
	  
	  private String flytime;
	  
	  private int adultprice;
	  
	  private int adulttax;
	  
	  private int childprice;
	  
	  private int childtax;
	  
	  private String createtime;
	  
	  private int seats;
}
