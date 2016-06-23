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

public class FormParams_ZE extends FormParams {
	
	
	
	/**
	 * 单程表单
	 */
	public void buildOw() {
		this.formParams.add(new BasicNameValuePair("method", "quickStep"));
		this.formParams.add(new BasicNameValuePair("cd_station", "DOM"));
		this.formParams.add(new BasicNameValuePair("nm_fromline", "Gimpo(GMP)"));
		this.formParams.add(new BasicNameValuePair("nm_toline", "Jeju(CJU)"));
		this.formParams.add(new BasicNameValuePair("cd_return", "0"));
		this.formParams.add(new BasicNameValuePair("cd_fromline", "GMP"));
		this.formParams.add(new BasicNameValuePair("cd_toline", "CJU"));
		this.formParams.add(new BasicNameValuePair("dt_from", "2016-07-31 (Su)"));
		this.formParams.add(new BasicNameValuePair("no_person_m", "2"));
		this.formParams.add(new BasicNameValuePair("no_person_p", "0"));
		this.formParams.add(new BasicNameValuePair("no_person_b", "0"));
		
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
	
	public void buildPost2() {
		this.formParams.clear();
		this.formParams.add(new BasicNameValuePair("method", "availability"));    
		this.formParams.add(new BasicNameValuePair("dt_date", "2016-07-31 (Su)"));
		this.formParams.add(new BasicNameValuePair("is_departure", "true"));      
		this.formParams.add(new BasicNameValuePair("fromline", "GMP"));           
		this.formParams.add(new BasicNameValuePair("toline", "CJU"));             
		this.formParams.add(new BasicNameValuePair("nmfromline", "Gimpo(GMP)"));  
		this.formParams.add(new BasicNameValuePair("nmtoline", "Jeju(CJU)"));     
		this.formParams.add(new BasicNameValuePair("dt_from", "2016-07-31 (Su)"));
		this.formParams.add(new BasicNameValuePair("cd_station", "DOM")); 
	}
	

	@Override
	public String toString() {
		String info = "" + this.formParams;
		return info;
	}

}
