package cebu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 表单变量类，用于传递post请求参数
 * 
 * @author lihaijun
 *
 */

public abstract class FormParams {

	protected final Logger logger = LoggerFactory.getLogger(FormParams.class);

	protected String depAirport = "";
	protected String arrAirport = "";
	protected String depTime = "";
	protected String retTime = "";
	protected String adultNum = "2";
	protected String childNum = "0";

	protected List<NameValuePair> formParams; // 用于post表单提交的真正的参数
	protected Pattern pattern;
	protected String timeSpliter; //时间格式分隔符
	
	protected String depTime_Year;	// 出发年月日
	protected String depTime_Month; 
	protected String depTime_Day; 
	
	protected String retTime_Year;	// 返程年月日
	protected String retTime_Month; 
	protected String retTime_Day; 
	
	protected FormParams() {
		// 日期格式验证
		this.setTimeSpliter();
		this.setPattern();
		formParams = new ArrayList<NameValuePair>();
	}

	public void setTimeSpliter() {
		this.timeSpliter = "-";
	}
	
	public void setPattern() {
		String regexTime = "[0-9]{4}" + this.timeSpliter + "[0-9]{2}" + this.timeSpliter + "[0-9]{2}";
		pattern = Pattern.compile(regexTime);
	}
	
	
	public String getDepTime_Year() {
		return depTime_Year;
	}

	public FormParams setDepTime_Year(String depTime_Year) {
		this.depTime_Year = depTime_Year;
		return this;
	}

	public String getDepTime_Month() {
		return depTime_Month;
	}

	public FormParams setDepTime_Month(String depTime_Month) {
		this.depTime_Month = depTime_Month;
		return this;
	}

	public String getRetTime_Year() {
		return retTime_Year;
	}

	public FormParams setRetTime_Year(String retTime_Year) {
		this.retTime_Year = retTime_Year;
		return this;
	}

	public String getRetTime_Month() {
		return retTime_Month;
	}

	public FormParams setRetTime_Month(String retTime_Month) {
		this.retTime_Month = retTime_Month;
		return this;
	}

	public String getDepTime_Day() {
		return depTime_Day;
	}

	public FormParams setDepTime_Day(String depTime_Day) {
		this.depTime_Day = depTime_Day;
		return this;
	}


	public String getRetTime_Day() {
		return retTime_Day;
	}

	public FormParams setRetTime_Day(String retTime_Day) {
		this.retTime_Day = retTime_Day;
		return this;
	}


	public String getDepAirport() {
		return depAirport;
	}

	public FormParams setDepAirport(String depAirport) {
		this.depAirport = depAirport;
		return this;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public FormParams setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
		return this;
	}

	public String getDepTime() {
		return depTime;
	}

	public FormParams setDepTime(String depTime) {
		Matcher m = pattern.matcher(depTime);
		if (m.find()) {
			this.depTime = depTime;
			String[] times = depTime.split(this.timeSpliter);
			this.setDepTime_Year(times[0]);
			this.setDepTime_Month(times[1]);
			this.setDepTime_Day(times[2]);
			return this;
		} else {
			log("Illeagal format String of time");
			return this;
		}
	}

	public String getRetTime() {
		return retTime;
	}

	public FormParams setRetTime(String retTime) {
		Matcher m = pattern.matcher(retTime);
		if (m.find()) {
			this.retTime = retTime;
			String[] times = retTime.split(this.timeSpliter);
			this.setRetTime_Year(times[0]);
			this.setRetTime_Month(times[1]);
			this.setRetTime_Day(times[2]);
			return this;
		} else {
			log("Illeagal format String of time");
			return this;
		}
	}

	public String getAdultNum() {
		return adultNum;
	}

	public FormParams setAdultNum(int adultNum) {
		this.adultNum = String.valueOf(adultNum);
		return this;
	}

	public String getChildNum() {
		return childNum;
	}

	public FormParams setChildNum(int childNum) {
		this.childNum = String.valueOf(childNum);
		return this;
	}

	public List<NameValuePair> getFormParams() {
		return formParams;
	}

	public FormParams setFormParams(List<NameValuePair> formParams) {
		this.formParams = formParams;
		return this;
	}

	/**
	 * 构建真正的表单变量
	 */
	public abstract void build();

	public final void log(String info) {
		logger.error(info);
	}
}
