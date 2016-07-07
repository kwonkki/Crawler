package cebu.model;

import org.apache.http.message.BasicNameValuePair;

import cebu.util.common.CommonUtil;


/**
 * 表单变量类，用于传递post请求参数
 * 继承自FormParams类
 * 7C 
 * @author lihaijun
 *
 */

public class FormParams_NK extends FormParams {

	
	/**
	 * 单程表单
	 */
	public void buildOw() {
		this.formParams.add(new BasicNameValuePair("HiddenGuid", "b777ffe6-cb38-4271-ba5e-b1c4b6314664"));
		this.formParams.add(new BasicNameValuePair("birthdates", ""));
		this.formParams.add(new BasicNameValuePair("lapoption", ""));
		this.formParams.add(new BasicNameValuePair("awardFSNumber", ""));
		this.formParams.add(new BasicNameValuePair("bookingType", "F"));
		this.formParams.add(new BasicNameValuePair("hotelOnlyInput", ""));
		this.formParams.add(new BasicNameValuePair("autoCompleteValueHidden", ""));
		this.formParams.add(new BasicNameValuePair("tripType", "oneWay"));
		this.formParams.add(new BasicNameValuePair("from", this.getDepAirport()));
		this.formParams.add(new BasicNameValuePair("to", this.getArrAirport()));
		
		String date = this.getDepTime_Month() + "/" +
						this.getDepTime_Day() + "/" +
						this.getDepTime_Year();
		this.formParams.add(new BasicNameValuePair("departDate", date));
		this.formParams.add(new BasicNameValuePair("departDateDisplay", date));
		this.formParams.add(new BasicNameValuePair("returnDate", date));
		this.formParams.add(new BasicNameValuePair("returnDateDisplay", date));
		this.formParams.add(new BasicNameValuePair("carPickUpTime", "16"));
		this.formParams.add(new BasicNameValuePair("carDropOffTime", "16"));
		this.formParams.add(new BasicNameValuePair("ADT", this.getAdultNum()));
		this.formParams.add(new BasicNameValuePair("CHD", this.getChildNum()));
		this.formParams.add(new BasicNameValuePair("INF", "0"));
		this.formParams.add(new BasicNameValuePair("promoCode", ""));
		this.formParams.add(new BasicNameValuePair("fromMultiCity1", ""));
		this.formParams.add(new BasicNameValuePair("toMultiCity1", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCity1", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCityDisplay1", ""));
		this.formParams.add(new BasicNameValuePair("fromMultiCity2", ""));
		this.formParams.add(new BasicNameValuePair("toMultiCity2", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCity2", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCityDisplay2", ""));
		this.formParams.add(new BasicNameValuePair("fromMultiCity3", ""));
		this.formParams.add(new BasicNameValuePair("toMultiCity3", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCity3", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCityDisplay3", ""));
		this.formParams.add(new BasicNameValuePair("fromMultiCity4", ""));
		this.formParams.add(new BasicNameValuePair("toMultiCity4", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCity4", ""));
		this.formParams.add(new BasicNameValuePair("dateMultiCityDisplay4", ""));
		
	}

	/**
	 * 往返表单，需要构建两个route，去程和回程
	 */
	public void buildRt() {

	}

	@Override
	public void build() {
		// 往返表单参数
		if (!CommonUtil.checkStrNullOrEmpty(this.retTime)) {
			this.buildRt();
		} else {
			this.buildOw();
		}
	}
	

	@Override
	public String toString() {
		String info = "" + this.formParams;
		return info;
	}

}
