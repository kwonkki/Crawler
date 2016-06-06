package com.crawler.cebu;

public class FormUtil {
	// 选项：单程、往返、多城市
	public static String Name_TravelOptions = "ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure";
	public static String[] Val_TravelOptions = { "RoundTrip", "OneWay", "OpenJaw" };

	// 出发地
	public static String Name_Origin = "ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1";
	public static String[] Val_Origin = { };
	
	// 目的地
	public static String Name_Destination = "ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1";
	public static String[] Val_Destination = { };
	
	// 是否显示Calendar，readOnly
	public static String Name_CalendarDisplay = "date_picker_1_AccCalendar";
	public static String Val_CalendarDispaly = "";
	
	// 出发日
	public static String Name_CalendarDayDeparture = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1";
	public static String[] Val_CalendarDayDeparture = { };
	
	// 出发月
	public static String Name_CalendarMonthDeparture = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1";
	public static String[] Val_CalendarMonthDeparture = { };
	
	// 返回日
	public static String Name_CalendarDayReturn = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2";
	public static String[] Val_CalendarDayReturn = { };
	
	// 返回月
	public static String Name_CalendarMonthReturn = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2";
	public static String[] Val_CalendarMonthReturn = { };
	
	// 大人数目，默认1
	public static String Name_Adults = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT";
	public static String[] Val_Adults = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	
	// 小孩数目，默认1
	public static String Name_Children = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_CHD";
	public static String[] Val_Children = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	
	// 提交
	public static String Name_Submit = "ControlGroupSearchView$AvailabilitySearchInputSearchView$ButtonSubmit";
	public static String Val_Submit = "FIND IT!";

}
