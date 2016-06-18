package cebu.model;

import org.apache.http.message.BasicNameValuePair;

/**
 * 表单变量类，用于传递post请求参数
 * 继承自FormParams类
 * 7C 
 * @author lihaijun
 *
 */

public class FormParams_7C extends FormParams {
	
	private String tripType;
	
	public String getRouteType() {
		return tripType;
	}

	public void setRouteType(String tripType) {
		this.tripType = tripType;
	}

	@Override
	public void build() {
		// 单程表单
		this.formParams.add(new BasicNameValuePair("AdultPaxCnt", this.getAdultNum()));
		this.formParams.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		this.formParams.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		this.formParams.add(new BasicNameValuePair("RouteType", "I"));
		this.formParams.add(new BasicNameValuePair("SystemType", "IBE"));
		this.formParams.add(new BasicNameValuePair("Language", "EN"));
		this.formParams.add(new BasicNameValuePair("DepStn", this.getDepAirport()));
		this.formParams.add(new BasicNameValuePair("ArrStn", this.getArrAirport()));
		this.formParams.add(new BasicNameValuePair("SegType", "DEP"));
		this.formParams.add(new BasicNameValuePair("TripType", "OW"));
		this.formParams.add(new BasicNameValuePair("DepDate", this.getDepTime()));
		this.formParams.add(new BasicNameValuePair("Index", "3"));
	}
	

	@Override
	public String toString() {
		String info = "DepStn: " + this.getDepAirport() +
			", ArrStn: " + this.getArrAirport() +
			", DepDate: " + this.getDepTime() +
			", AdultPaxCnt: " + this.getAdultNum();
				
		return info;
	}

}
