package cebu.model;

import org.apache.http.message.BasicNameValuePair;

import cebu.util.parser.FormUtil;
import cebu.util.parser.FormUtil.TravelOption;


/**
 * 表单变量类，用于传递post请求参数
 * 
 * @author lihaijun
 *
 */

public class FormParams_5J extends FormParams {

	private TravelOption travelOption; // 单程、往返、中转

	public FormParams_5J() {

	}
	
	public TravelOption getTravelOption() {
		return this.travelOption;
	}
	
	public FormParams_5J setTravelOption(TravelOption travelOption) {
		this.travelOption = travelOption;
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
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure",
				this.getTravelOption().toString()));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1",
				this.getDepAirport()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin1", this.getDepAirport()));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory",
				FormUtil.mapOrgStation.get(this.getDepAirport())));
		formParams.add(new BasicNameValuePair("originStationContainercategory", this.getDepAirport()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1", this.getArrAirport()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination1",
				this.getArrAirport()));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory",
				FormUtil.mapDestStation.get(this.getDepAirport())));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", this.getArrAirport()));
		formParams.add(new BasicNameValuePair("date_picker_1", this.getDepTime()));
		formParams.add(new BasicNameValuePair("date_picker_1_AccCalendar", this.getDepTime()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1",
				this.getDepTime_Day())); // departure time day
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1",
				this.getDepTime_Year() + "-" + this.getDepTime_Month()));// departure time year, month
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
		formParams.add(new BasicNameValuePair("date_picker_2", this.getRetTime()));
		formParams.add(new BasicNameValuePair("date_picker_2_AccCalendar", this.getRetTime()));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2",
				this.getRetTime_Day())); // return time day
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2",
				this.getDepTime_Year() + "-" + this.getDepTime_Month())); // return time year, month
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

	@Override
	public String toString() {
		String info = "travelOption=" + this.getTravelOption() + ", " + "depAirport=" + this.getDepAirport() + ", "
				+ "arrAirport=" + this.getArrAirport() + ", " + "depTime=" + this.getDepTime() + ", "
				+ "retTime=" + this.getRetTime() + ", " + "adultNum=" + this.getAdultNum() + ", " + "childNum="
				+ this.getChildNum();
		return info;
	}

}
