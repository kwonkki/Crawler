package cebu.model;

public class Flight {
	// important
	private String id;
	private String carrier;
	private String flightNumber;
	private String depAirport;
	private String depTime;
	private String arrAirport;
	private String arrTime;
	private String adultPrice;
	private String adultTax;
	private String currency;
	// unimportant
	private String stopCities;
	private String codeShare;
	private String cabin;
	private String aircraftCode;
	private String data;
	private String childPrice;
	private String childTax;
	private String nationalityType;
	private String nationality;
	private String suitAge;
	private String priceType;
	private String applyType;
	private String adultTaxType;
	private String childTaxType;
	private String ticketInvoiceType;
	private String fromSegments;
	private String retSegments;
	private String hasRefund;
	private String refund;
	private String partRefund;
	private String partRefundPrice;
	private String hasEndorse;
	private String endorse;
	private String partEndorse;
	private String partEndorsePrice;
	private String endorsement;
	private String hasBaggage;
	private String baggage;
	private String hasNoShow;
	private String noShowLimitTime;
	private String penalty;
	private String specialNoShow;
	private String other;
	private String flyTime;
	private String createTime;
	private String count;
	private int seats;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStopCities() {
		return stopCities;
	}

	public void setStopCities(String stopCities) {
		this.stopCities = stopCities;
	}

	public String getCodeShare() {
		return codeShare;
	}

	public void setCodeShare(String codeShare) {
		this.codeShare = codeShare;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getAircraftCode() {
		return aircraftCode;
	}

	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(String childPrice) {
		this.childPrice = childPrice;
	}

	public String getChildTax() {
		return childTax;
	}

	public void setChildTax(String childTax) {
		this.childTax = childTax;
	}

	public String getNationalityType() {
		return nationalityType;
	}

	public void setNationalityType(String nationalityType) {
		this.nationalityType = nationalityType;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSuitAge() {
		return suitAge;
	}

	public void setSuitAge(String suitAge) {
		this.suitAge = suitAge;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getAdultTaxType() {
		return adultTaxType;
	}

	public void setAdultTaxType(String adultTaxType) {
		this.adultTaxType = adultTaxType;
	}

	public String getChildTaxType() {
		return childTaxType;
	}

	public void setChildTaxType(String childTaxType) {
		this.childTaxType = childTaxType;
	}

	public String getTicketInvoiceType() {
		return ticketInvoiceType;
	}

	public void setTicketInvoiceType(String ticketInvoiceType) {
		this.ticketInvoiceType = ticketInvoiceType;
	}

	public String getFromSegments() {
		return fromSegments;
	}

	public void setFromSegments(String fromSegments) {
		this.fromSegments = fromSegments;
	}

	public String getRetSegments() {
		return retSegments;
	}

	public void setRetSegments(String retSegments) {
		this.retSegments = retSegments;
	}

	public String getHasRefund() {
		return hasRefund;
	}

	public void setHasRefund(String hasRefund) {
		this.hasRefund = hasRefund;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public String getPartRefund() {
		return partRefund;
	}

	public void setPartRefund(String partRefund) {
		this.partRefund = partRefund;
	}

	public String getPartRefundPrice() {
		return partRefundPrice;
	}

	public void setPartRefundPrice(String partRefundPrice) {
		this.partRefundPrice = partRefundPrice;
	}

	public String getHasEndorse() {
		return hasEndorse;
	}

	public void setHasEndorse(String hasEndorse) {
		this.hasEndorse = hasEndorse;
	}

	public String getEndorse() {
		return endorse;
	}

	public void setEndorse(String endorse) {
		this.endorse = endorse;
	}

	public String getPartEndorse() {
		return partEndorse;
	}

	public void setPartEndorse(String partEndorse) {
		this.partEndorse = partEndorse;
	}

	public String getPartEndorsePrice() {
		return partEndorsePrice;
	}

	public void setPartEndorsePrice(String partEndorsePrice) {
		this.partEndorsePrice = partEndorsePrice;
	}

	public String getEndorsement() {
		return endorsement;
	}

	public void setEndorsement(String endorsement) {
		this.endorsement = endorsement;
	}

	public String getHasBaggage() {
		return hasBaggage;
	}

	public void setHasBaggage(String hasBaggage) {
		this.hasBaggage = hasBaggage;
	}

	public String getBaggage() {
		return baggage;
	}

	public void setBaggage(String baggage) {
		this.baggage = baggage;
	}

	public String getHasNoShow() {
		return hasNoShow;
	}

	public void setHasNoShow(String hasNoShow) {
		this.hasNoShow = hasNoShow;
	}

	public String getNoShowLimitTime() {
		return noShowLimitTime;
	}

	public void setNoShowLimitTime(String noShowLimitTime) {
		this.noShowLimitTime = noShowLimitTime;
	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public String getSpecialNoShow() {
		return specialNoShow;
	}

	public void setSpecialNoShow(String specialNoShow) {
		this.specialNoShow = specialNoShow;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getFlyTime() {
		return flyTime;
	}

	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		String info = "carrier=" + this.getCarrier() + ", " + 
				"flightNumber=" + this.getFlightNumber() + ", " + 
				"depAirport=" + this.getDepAirport() + ", " + "arrAirport=" + this.getArrAirport() + ", " + 
				"depTime=" + this.getDepTime() + ", " + 
				"arrTime=" + this.getArrTime() + ", " + 
				"adultPrice=" + this.getAdultPrice() + ", " + 
				"adultTax=" + this.getAdultTax() + ", " + 
				"currency=" + this.getCurrency();
		return info;
	}

}
