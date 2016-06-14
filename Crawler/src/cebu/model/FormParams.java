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
 * @author lihaijun
 *
 */

public class FormParams {

	private TravelOption travelOption;	// 单程、往返、中转
	private OrgStation orgStation;	// 始发站，枚举
	private DestStation destStation;	// 终点站，枚举
	private String departureTime; 	
	private String returnTime = "";
	private String adultNum = "2";
	private String childNum = "0";
	
	private List<NameValuePair> formParams;		// 用于post表单提交的真正的参数
	private Pattern pattern;
	
	private String departureTime_Day;	// 出发时间，天
	private String departureTime_YearMonth;	// 出发时间，年月
	private String returnTime_Day;	// 返回时间，天
	private String returnTime_YearMonth;	// 返回时间，年月
	
	public FormParams() {
		// 日期格式验证
		String regexTime = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		formParams = new ArrayList<NameValuePair>();
		pattern = Pattern.compile(regexTime);
	}
	
	
	public TravelOption getTravelOption() {
		return travelOption;
	}

	public FormParams setTravelOption(TravelOption travelOption) {
		this.travelOption = travelOption;
		return this;
	}

	public OrgStation getOrgStation() {
		return orgStation;
	}

	public FormParams setOrgStation(OrgStation orgStation) {
		this.orgStation = orgStation;
		return this;
	}

	public DestStation getDestStation() {
		return destStation;
	}

	public FormParams setDestStation(DestStation destStation) {
		this.destStation = destStation;
		return this;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public FormParams setDepartureTime(String departureTime) {
		Matcher m = pattern.matcher(departureTime);
		if (m.find()) {
			this.departureTime = departureTime;
			String[] times = departureTime.split("-");
			this.departureTime_Day = times[2];
			this.departureTime_YearMonth = times[0] + "-" + times[1];
			return this;
		}
		else {
			System.out.println("Illeagal format String of time");
			return this;
		}
	}

	public String getReturnTime() {
		return returnTime;
	}

	public FormParams setReturnTime(String returnTime) {
		Matcher m = pattern.matcher(returnTime);
		if (m.find()) {
			this.returnTime = returnTime;
			String[] times = returnTime.split("-");
			this.returnTime_Day = times[2];
			this.returnTime_YearMonth = times[0] + "-" + times[1];
			return this;
		}
		else {
			System.out.println("Illeagal format String of time");
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
	public void build() {
		formParams.add(new BasicNameValuePair("__EVENTTARGET", ""));
		formParams.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		formParams.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwUBMGRkZ0I3jKvfnhKfx57f3Wjr7p2WhvU="));
		formParams.add(new BasicNameValuePair("pageToken", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure", travelOption.toString()));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1",
				orgStation.toString()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin1", orgStation.toString()));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", FormUtil.mapOrgStation.get(orgStation.toString())));
		formParams.add(new BasicNameValuePair("originStationContainercategory", orgStation.toString()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1", destStation.toString()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination1", destStation.toString()));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", FormUtil.mapDestStation.get(orgStation.toString())));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", destStation.toString()));
		formParams.add(new BasicNameValuePair("date_picker_1", departureTime));
		formParams.add(new BasicNameValuePair("date_picker_1_AccCalendar", departureTime));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1", departureTime_Day));	// departure time day
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1", departureTime_YearMonth));	// departure time year, month
		formParams.add(
				new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation2", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin2", ""));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", "From"));
		formParams.add(new BasicNameValuePair("originStationContainercategory", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation2", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination2", ""));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", "To"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", ""));
		formParams.add(new BasicNameValuePair("date_picker_2", returnTime));
		formParams.add(new BasicNameValuePair("date_picker_2_AccCalendar", returnTime));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2", returnTime_Day));	// return time day
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2", returnTime_YearMonth));	// return time year, month
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT", adultNum));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_CHD", childNum));
		formParams.add(
				new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$promoCodeID", ""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$ButtonSubmit",
				"FIND IT!"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$TextBoxUserID", "EMAIL"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$PasswordFieldPassword", ""));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxConfirmation", ""));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxHiddenUsername", ""));
	}
	
}
