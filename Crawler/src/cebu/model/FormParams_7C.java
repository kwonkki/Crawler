package cebu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import cebu.util.FormUtil;
import cebu.util.FormUtil.DestStation;
import cebu.util.FormUtil.OrgStation;
import cebu.util.FormUtil.TravelOption;

/**
 * 表单变量类，用于传递post请求参数
 * 
 * @author lihaijun
 *
 */

public class FormParams_7C {
	private String mainFlag = "main";
	private String routeType = "I";
	private String tripType = "O";
	private String payType = "";
	private String depStn1 = "WEH"; // 始发站
	private String arrStn1 = "";
	private String depStn2 = "";
	private String arrStn2 = "ICN"; // 终点站
	private String depStnName1 = "Weihai";
	private String arrStnName1 = "Destination";
	private String depStnName2 = "Origin";
	private String arrStnName2 = "Seoul(Incheon)";
	private String depDate1 = "2016-12-20"; // 始发日期
	private String depDate2 = "2016-12-20";
	private String country1 = "";
	private String country2 = "CN";
	private int selectSectionSize = 1;
	private int adtPaxCnt = 2;
	private int chdPaxCnt = 0;
	private int infPaxCnt = 0;
	private String dualFlag = "";
	private String multiFlag = "";

	public String getMainFlag() {
		return mainFlag;
	}

	public void setMainFlag(String mainFlag) {
		this.mainFlag = mainFlag;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDepStn1() {
		return depStn1;
	}

	public void setDepStn1(String depStn1) {
		this.depStn1 = depStn1;
	}

	public String getArrStn1() {
		return arrStn1;
	}

	public void setArrStn1(String arrStn1) {
		this.arrStn1 = arrStn1;
	}

	public String getDepStn2() {
		return depStn2;
	}

	public void setDepStn2(String depStn2) {
		this.depStn2 = depStn2;
	}

	public String getArrStn2() {
		return arrStn2;
	}

	public void setArrStn2(String arrStn2) {
		this.arrStn2 = arrStn2;
	}

	public String getDepStnName1() {
		return depStnName1;
	}

	public void setDepStnName1(String depStnName1) {
		this.depStnName1 = depStnName1;
	}

	public String getArrStnName1() {
		return arrStnName1;
	}

	public void setArrStnName1(String arrStnName1) {
		this.arrStnName1 = arrStnName1;
	}

	public String getDepStnName2() {
		return depStnName2;
	}

	public void setDepStnName2(String depStnName2) {
		this.depStnName2 = depStnName2;
	}

	public String getArrStnName2() {
		return arrStnName2;
	}

	public void setArrStnName2(String arrStnName2) {
		this.arrStnName2 = arrStnName2;
	}

	public String getDepDate1() {
		return depDate1;
	}

	public void setDepDate1(String depDate1) {
		this.depDate1 = depDate1;
	}

	public String getDepDate2() {
		return depDate2;
	}

	public void setDepDate2(String depDate2) {
		this.depDate2 = depDate2;
	}

	public String getCountry1() {
		return country1;
	}

	public void setCountry1(String country1) {
		this.country1 = country1;
	}

	public String getCountry2() {
		return country2;
	}

	public void setCountry2(String country2) {
		this.country2 = country2;
	}

	public int getSelectSectionSize() {
		return selectSectionSize;
	}

	public void setSelectSectionSize(int selectSectionSize) {
		this.selectSectionSize = selectSectionSize;
	}

	public int getAdtPaxCnt() {
		return adtPaxCnt;
	}

	public void setAdtPaxCnt(int adtPaxCnt) {
		this.adtPaxCnt = adtPaxCnt;
	}

	public int getChdPaxCnt() {
		return chdPaxCnt;
	}

	public void setChdPaxCnt(int chdPaxCnt) {
		this.chdPaxCnt = chdPaxCnt;
	}

	public int getInfPaxCnt() {
		return infPaxCnt;
	}

	public void setInfPaxCnt(int infPaxCnt) {
		this.infPaxCnt = infPaxCnt;
	}

	public String getDualFlag() {
		return dualFlag;
	}

	public void setDualFlag(String dualFlag) {
		this.dualFlag = dualFlag;
	}

	public String getMultiFlag() {
		return multiFlag;
	}

	public void setMultiFlag(String multiFlag) {
		this.multiFlag = multiFlag;
	}

	@Override
	public String toString() {
		String info = 
				"mainFlag=" + this.getMainFlag() + ", " +
				"routeType=" + this.getRouteType() + ", " +
				"tripType=" + this.getTripType()+ ", " +
				"payType=" + this.getPayType() + ", " +
				"depStn1=" + this.getDepStn1() + ", " +
				"arrStn1=" + this.getArrStn1() + ", " +
				"depStn1=" + this.getDepStn2() + ", " +
				"arrStn2=" + this.getArrStn2() + ", " +
				"depStnName1=" + this.getDepStnName1() + ", " +
				"arrStnName1=" + this.getArrStnName1() + ", " +
				"depStnName2=" + this.getDepStnName2() + ", " +
				"arrStnName2=" + this.getArrStnName2() + ", " +
				"depDate1=" + this.getDepDate1() + ", " +
				"depDate2=" + this.getDepDate2() + ", " +
				"country1=" + this.getCountry1() + ", " +
				"country2=" + this.getCountry2() + ", " +
				"selectSectionSize=" + this.getSelectSectionSize() + ", " +
				"adtPaxCnt=" + this.getAdtPaxCnt() + ", " +
				"chdPaxCnt=" + this.getChdPaxCnt() + ", " +
				"infPaxCnt=" + this.getInfPaxCnt() + ", " +
				"dualFlag=" + this.getDualFlag() +", " +
				"multiFlag=" + this.getMultiFlag();
				
		return info;
	}

}
