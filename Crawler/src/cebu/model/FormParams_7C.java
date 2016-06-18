package cebu.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import cebu.util.common.CommonUtil;

/**
 * 表单变量类，用于传递post请求参数
 * 继承自FormParams类
 * 7C 
 * @author lihaijun
 *
 */

public class FormParams_7C extends FormParams {
	
	// 默认单程的表单参数
	private String tripType = "OW";
	private String segType = "DEP";
	
	// 用于post表单提交的真正的参数,往返中的回程表单
	// 继承的FormParams中的formParams是单程或者往返中的去程表单
	// 即，往返模式中有两个表单
	private List<NameValuePair> formParamsRt = new ArrayList<NameValuePair>(); 
	
	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getSegType() {
		return segType;
	}

	public void setSegType(String segType) {
		this.segType = segType;
	}
	
	public List<NameValuePair> getFormParamsRt() {
		return formParamsRt;
	}

	public void setFormParamsRt(List<NameValuePair> formParamsRt) {
		this.formParamsRt = formParamsRt;
	}

	/**
	 * 单程表单
	 */
	public void buildOw() {
		this.formParams.add(new BasicNameValuePair("AdultPaxCnt", this.getAdultNum()));
		this.formParams.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		this.formParams.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		this.formParams.add(new BasicNameValuePair("RouteType", "I"));
		this.formParams.add(new BasicNameValuePair("SystemType", "IBE"));
		this.formParams.add(new BasicNameValuePair("Language", "EN"));
		this.formParams.add(new BasicNameValuePair("DepStn", this.getDepAirport()));
		this.formParams.add(new BasicNameValuePair("ArrStn", this.getArrAirport()));
		this.formParams.add(new BasicNameValuePair("SegType", this.getSegType()));
		this.formParams.add(new BasicNameValuePair("TripType", this.getTripType()));
		this.formParams.add(new BasicNameValuePair("DepDate", this.getDepTime()));
		this.formParams.add(new BasicNameValuePair("Index", "3"));
		
	}
	
	/**
	 * 往返表单，需要构建两个route，去程和回程
	 */
	public void buildRt() {
		this.formParams.add(new BasicNameValuePair("AdultPaxCnt", this.getAdultNum()));
		this.formParams.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		this.formParams.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		this.formParams.add(new BasicNameValuePair("RouteType", "I"));
		this.formParams.add(new BasicNameValuePair("SystemType", "IBE"));
		this.formParams.add(new BasicNameValuePair("Language", "EN"));
		this.formParams.add(new BasicNameValuePair("DepStn", this.getDepAirport()));
		this.formParams.add(new BasicNameValuePair("ArrStn", this.getArrAirport()));
		this.formParams.add(new BasicNameValuePair("SegType", this.getSegType()));
		this.formParams.add(new BasicNameValuePair("TripType", this.getTripType()));
		this.formParams.add(new BasicNameValuePair("DepDate", this.getDepTime()));
		this.formParams.add(new BasicNameValuePair("Index", "3"));

		this.formParamsRt.add(new BasicNameValuePair("AdultPaxCnt", this.getAdultNum()));
		this.formParamsRt.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		this.formParamsRt.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		this.formParamsRt.add(new BasicNameValuePair("RouteType", "I"));
		this.formParamsRt.add(new BasicNameValuePair("SystemType", "IBE"));
		this.formParamsRt.add(new BasicNameValuePair("Language", "EN"));
		this.formParamsRt.add(new BasicNameValuePair("DepStn", this.getArrAirport()));		// 始发站和终点站对调
		this.formParamsRt.add(new BasicNameValuePair("ArrStn", this.getDepAirport()));
		this.formParamsRt.add(new BasicNameValuePair("SegType", "RET"));					// 固定
		this.formParamsRt.add(new BasicNameValuePair("TripType", this.getTripType()));		
		this.formParamsRt.add(new BasicNameValuePair("DepDate", this.getRetTime()));		// 回程时间
		this.formParamsRt.add(new BasicNameValuePair("Index", "3"));
	}

	@Override
	public void build() {
		// 往返表单参数
		if (!CommonUtil.checkStrNullOrEmpty(this.retTime)) {
			this.setSegType("DEP");
			this.setTripType("RT");
			this.buildRt();
		} else {
			this.buildOw();
		}
	}
	

	@Override
	public String toString() {
		String info = "formParams: " + this.formParams + "\n" +
				"formParamsRt: " + this.formParamsRt;
		return info;
	}

}
