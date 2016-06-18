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

	protected String depTime_Day; // 出发时间，天
	protected String depTime_YearMonth; // 出发时间，年月
	protected String retTime_Day; // 返回时间，天
	protected String retTime_YearMonth; // 返回时间，年月

	protected FormParams() {
		// 日期格式验证
		String regexTime = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		formParams = new ArrayList<NameValuePair>();
		pattern = Pattern.compile(regexTime);
	}

	public String getDepTime_Day() {
		return depTime_Day;
	}

	public FormParams setDepTime_Day(String depTime_Day) {
		this.depTime_Day = depTime_Day;
		return this;
	}

	public String getDepTime_YearMonth() {
		return depTime_YearMonth;
	}

	public FormParams setDepTime_YearMonth(String depTime_YearMonth) {
		this.depTime_YearMonth = depTime_YearMonth;
		return this;
	}

	public String getRetTime_Day() {
		return retTime_Day;
	}

	public FormParams setRetTime_Day(String retTime_Day) {
		this.retTime_Day = retTime_Day;
		return this;
	}

	public String getRetTime_YearMonth() {
		return retTime_YearMonth;
	}

	public FormParams setRetTime_YearMonth(String retTime_YearMonth) {
		this.retTime_YearMonth = retTime_YearMonth;
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
			String[] times = depTime.split("-");
			this.depTime_Day = times[2];
			this.depTime_YearMonth = times[0] + "-" + times[1];
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
			String[] times = retTime.split("-");
			this.retTime_Day = times[2];
			this.retTime_YearMonth = times[0] + "-" + times[1];
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
