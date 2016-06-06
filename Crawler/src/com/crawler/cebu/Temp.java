package com.crawler.cebu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Temp {
	
	public static String POST_URL = "https://book.cebupacificair.com/Select.aspx" +
			"&__EVENTTARGET=" +
			"&__EVENTARGUMENT=" +
			"&__VIEWSTATE=%2FwEPDwUBMGRkZ0I3jKvfnhKfx57f3Wjr7p2WhvU%3D" +
			"&pageToken=" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24RadioButtonMarketStructure=OneWay" +
			"&ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1=HKG" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketOrigin1=HKG" +
			"&name_originStationContainercategory=HongKong+%28HKG%29" +
			"&originStationContainercategory=HKG" +
			"&ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1=MNL" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketDestination1=MNL" +
			"&name_destinationStationContainercategory=Manila+%28MNL%29" +
			"&destinationStationContainercategory=MNL" +
			"&date_picker_1=2016-06-20" +
			"&date_picker_1_AccCalendar=2016-06-20" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketDay1=20" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketMonth1=2016-06" +
			"&ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation2=" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketOrigin2=" +
			"&name_originStationContainercategory=From" +
			"&originStationContainercategory=" +
			"&ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation2=" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketDestination2=" +
			"&name_destinationStationContainercategory=To" +
			"&destinationStationContainercategory=" +
			"&date_picker_2=2016-06-20" +
			"&date_picker_2_AccCalendar=2016-06-20" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketDay2=20" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketMonth2=2016-06" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListPassengerType_ADT=1" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListPassengerType_CHD=0" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24promoCodeID=" +
			"&ControlGroupSearchView%24AvailabilitySearchInputSearchView%24ButtonSubmit=FIND+IT%21" +
			"&MemberLoginView2LoginView%24TextBoxUserID=EMAIL" +
			"&MemberLoginView2LoginView%24PasswordFieldPassword=" +
			"&MemberLoginView2LoginView%24tboxConfirmation=" + 
			"&MemberLoginView2LoginView%24tboxHiddenUsername="; 
	
	
	public static void test() {
		/**
		NameValuePair[] data = { new BasicNameValuePair(FormUtil.Name_TravelOptions, FormUtil.Val_TravelOptions[1]),
				new BasicNameValuePair(FormUtil.Name_Origin, "HKG"),
				new BasicNameValuePair(FormUtil.Name_Destination, "MNL"),
				new BasicNameValuePair(FormUtil.Name_CalendarDayDeparture, "01"),
				new BasicNameValuePair(FormUtil.Name_CalendarMonthDeparture, "2016-07"),
				new BasicNameValuePair(FormUtil.Name_Adults, "1"),
				new BasicNameValuePair(FormUtil.Name_Children, "0") };
		**/
		
		/**
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair(FormUtil.Name_TravelOptions, FormUtil.Val_TravelOptions[1]));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Origin, "HKG"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Destination, "MNL"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_CalendarDayDeparture, "01"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_CalendarMonthDeparture, "2016-07"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Adults, "1"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Children, "0"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Submit, FormUtil.Val_Submit));
		**/
		
		
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		//formParams.add(new BasicNameValuePair("__EVENTTARGET",""));
		//formParams.add(new BasicNameValuePair("__EVENTARGUMENT",""));
		//formParams.add(new BasicNameValuePair("__VIEWSTATE","%2FwEPDwUBMGRkZ0I3jKvfnhKfx57f3Wjr7p2WhvU%3D")); 
		//formParams.add(new BasicNameValuePair("pageToken","" ));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24RadioButtonMarketStructure","OneWay"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1","HKG"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketOrigin1","HKG"));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory","HongKong%28HKG%29"));
		formParams.add(new BasicNameValuePair("originStationContainercategory","HKG"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1","MNL"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketDestination11","MNL"));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory","Manila%28MNL%29"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory","M1NL"));
		formParams.add(new BasicNameValuePair("date_picker_1","2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_1_AccCalendar","2016-06-20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketDay1","20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketMonth1","2016-06"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation2",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketOrigin2",""));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory","From"));
		formParams.add(new BasicNameValuePair("originStationContainercategory",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation2",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24TextBoxMarketDestination2",""));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory","To"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory",""));
		formParams.add(new BasicNameValuePair("date_picker_2","2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_2_AccCalendar","2016-06-20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketDay2","20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListMarketMonth2","2016-06"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListPassengerType_ADT","1"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24DropDownListPassengerType_CHD","0"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24promoCodeID",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView%24AvailabilitySearchInputSearchView%24ButtonSubmit","FINDIT%21"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView%24TextBoxUserID","EMAIL"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView%24PasswordFieldPassword","")); 
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView%24tboxConfirmation","" ));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView%24tboxHiddenUsername",""));
	}	
	
	
	
	
}
