package cebu.util.parser;

import java.util.LinkedHashMap;
import java.util.Map;

public class FormUtil {
	// 选项：单程、往返、多城市
	public static final String Name_TravelOptions = "ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure";
	public static final String[] Val_TravelOptions = { "RoundTrip", "OneWay", "OpenJaw" };

	// 出发地
	public static final String Name_Origin = "ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1";
	public static final String[] Val_Origin = { };
	
	// 目的地
	public static final String Name_Destination = "ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1";
	public static final String[] Val_Destination = { };
	
	// 是否显示Calendar，readOnly
	public static final String Name_CalendarDisplay = "date_picker_1_AccCalendar";
	public static final String Val_CalendarDispaly = "";
	
	// 出发日
	public static final String Name_CalendarDayDeparture = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1";
	public static final String[] Val_CalendarDayDeparture = { };
	
	// 出发月
	public static final String Name_CalendarMonthDeparture = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1";
	public static final String[] Val_CalendarMonthDeparture = { };
	
	// 返回日
	public static final String Name_CalendarDayReturn = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2";
	public static final String[] Val_CalendarDayReturn = { };
	
	// 返回月
	public static final String Name_CalendarMonthReturn = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2";
	public static final String[] Val_CalendarMonthReturn = { };
	
	// 大人数目，默认1
	public static final String Name_Adults = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT";
	public static final String[] Val_Adults = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	
	// 小孩数目，默认1
	public static final String Name_Children = "ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_CHD";
	public static final String[] Val_Children = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	
	// 提交
	public static final String Name_Submit = "ControlGroupSearchView$AvailabilitySearchInputSearchView$ButtonSubmit";
	public static final String Val_Submit = "FIND IT!";
	
	
	/*********************** 航班相关常量字符串 *********************************/
	public static final String TableHead = "TableHead";
	public static final String From = "From";
	public static final String To = "To";
	public static final String Flight = "Flight";
	public static final String Flight_Details = "Fly_Details";
	
	// 隐藏价格信息
	public static final String Price_Fly_Hidden = "Price_Fly_Hidden";
	public static final String Price_Fly_Bag_Hidden = "Price_Fly_Bag_Hidden";
	public static final String Price_Fly_Bag_Meal_Hidden = "Price_Fly_Bag_Meal_Hidden";
	public static final String Details_Fly_Hidden = "Details_Fly_Hidden";
	public static final String Details_Fly_Bag_Hidden = "Details_Fly_Bag_Hidden";
	public static final String Details_Fly_Bag_Meal_Hidden = "Details_Fly_Bag_Meal_Hidden";
	
	// 显示在网页上的价格信息
	public static final String Price_Fly = "Price_Fly";
	public static final String Price_Fly_Bag = "Price_Fly_Bag";
	public static final String Price_Fly_Bag_Meal = "Price_Fly_Bag_Meal";
	public static final String Details_Fly = "Details_Fly";
	public static final String Details_Fly_Bag = "Details_Fly_Bag";
	public static final String Details_Fly_Bag_Meal = "Details_Fly_Bag_Meal";
	
	
	// 选项枚举
	public static enum TravelOption {
		 RoundTrip, OneWay, OpenJaw
	};
	
	// 始发站
	public static enum OrgStation {
		PER, SYD, DAC, BWN, PNH, REP, PEK, CAN, HAK, LJG, 
		NGB, PVG, SZX, XMN, XIY, HKG, BLR, MAA, HYD, COK, 
		TRV, TRZ, DPS, BDO, CGK, SUB, FUK, NGO, KIX, NRT, 
		KWI, RGN, MLE, MFM, BKI, KUL, LGK, PEN, BCD, TAG, 
		MPH, BXU, CGY, CGM, CYZ, CEB, CRK, USU, CBO, DVO, 
		DPL, DGT, GES, ILO, KLO, LAO, LGP, MNL, WNP, OZC, 
		PAG, PPS, RXS, SJI, IAO, SUG, TAC, TDG, TWT, TUG, 
		VRC, ZAM, DOH, DMM, RUH, SIN, PUS, ICN, TPE, BKK, 
		CNX, HDY, KBV, HKT, GUM, DXB, HAN, SGN
	};
	
	// 始发站
	public static enum DestStation {
		PER, SYD, DAC, BWN, PNH, REP, PEK, CAN, HAK, LJG, 
		NGB, PVG, SZX, XMN, XIY, HKG, BLR, MAA, HYD, COK, 
		TRV, TRZ, DPS, BDO, CGK, SUB, FUK, NGO, KIX, NRT, 
		KWI, RGN, MLE, MFM, BKI, KUL, LGK, PEN, BCD, TAG, 
		MPH, BXU, CGY, CGM, CYZ, CEB, CRK, USU, CBO, DVO, 
		DPL, DGT, GES, ILO, KLO, LAO, LGP, MNL, WNP, OZC, 
		PAG, PPS, RXS, SJI, IAO, SUG, TAC, TDG, TWT, TUG, 
		VRC, ZAM, DOH, DMM, RUH, SIN, PUS, ICN, TPE, BKK, 
		CNX, HDY, KBV, HKT, GUM, DXB, HAN, SGN
	};
	
	// 始发站缩写和全称map
	public static final Map<OrgStation, String> mapOrgStation = new LinkedHashMap<OrgStation, String>();
	
	static {
		mapOrgStation.put(OrgStation.PER, "Perth");
		mapOrgStation.put(OrgStation.SYD, "Sydney");
		mapOrgStation.put(OrgStation.DAC, "Dhaka");
		mapOrgStation.put(OrgStation.BWN, "BandarSeriBegawan (Brunei)");
		mapOrgStation.put(OrgStation.PNH, "PhnomPenh");
		mapOrgStation.put(OrgStation.REP, "SiemReap");
		mapOrgStation.put(OrgStation.PEK, "Beijing");
		mapOrgStation.put(OrgStation.CAN, "Guangzhou (Canton)");
		mapOrgStation.put(OrgStation.HAK, "Haikou");
		mapOrgStation.put(OrgStation.LJG, "Lijiang");
		mapOrgStation.put(OrgStation.NGB, "Ningbo");
		mapOrgStation.put(OrgStation.PVG, "Shanghai");
		mapOrgStation.put(OrgStation.SZX, "Shenzhen");
		mapOrgStation.put(OrgStation.XMN, "Xiamen");
		mapOrgStation.put(OrgStation.XIY, "Xian");
		mapOrgStation.put(OrgStation.HKG, "HongKong");
		mapOrgStation.put(OrgStation.BLR, "Bangalore");
		mapOrgStation.put(OrgStation.MAA, "Chennai");
		mapOrgStation.put(OrgStation.HYD, "Hyderabad");
		mapOrgStation.put(OrgStation.COK, "Kochi");
		mapOrgStation.put(OrgStation.TRV, "Thiruvananthaphuram");
		mapOrgStation.put(OrgStation.TRZ, "Tiruchirapalli");
		mapOrgStation.put(OrgStation.DPS, "Bali (Denpasar)");
		mapOrgStation.put(OrgStation.BDO, "Bandung");
		mapOrgStation.put(OrgStation.CGK, "Jakarta");
		mapOrgStation.put(OrgStation.SUB, "Surabaya");
		mapOrgStation.put(OrgStation.FUK, "Fukuoka");
		mapOrgStation.put(OrgStation.NGO, "Nagoya");
		mapOrgStation.put(OrgStation.KIX, "Osaka (Kansai)");
		mapOrgStation.put(OrgStation.NRT, "Tokyo (Narita)");
		mapOrgStation.put(OrgStation.KWI, "Kuwait");
		mapOrgStation.put(OrgStation.RGN, "Yangon (Myanmar)");
		mapOrgStation.put(OrgStation.MLE, "Male (Maldives)");
		mapOrgStation.put(OrgStation.MFM, "Macau");
		mapOrgStation.put(OrgStation.BKI, "KotaKinabalu");
		mapOrgStation.put(OrgStation.KUL, "KualaLumpur");
		mapOrgStation.put(OrgStation.LGK, "Langkawi");
		mapOrgStation.put(OrgStation.PEN, "Penang");
		mapOrgStation.put(OrgStation.BCD, "Bacolod");
		mapOrgStation.put(OrgStation.TAG, "Bohol (Tagbilaran)");
		mapOrgStation.put(OrgStation.MPH, "Boracay (Caticlan)");
		mapOrgStation.put(OrgStation.BXU, "Butuan");
		mapOrgStation.put(OrgStation.CGY, "CagayandeOro");
		mapOrgStation.put(OrgStation.CGM, "Camiguin");
		mapOrgStation.put(OrgStation.CYZ, "Cauayan");
		mapOrgStation.put(OrgStation.CEB, "Cebu");
		mapOrgStation.put(OrgStation.CRK, "Clark");
		mapOrgStation.put(OrgStation.USU, "Coron (Busuanga)");
		mapOrgStation.put(OrgStation.CBO, "Cotabato");
		mapOrgStation.put(OrgStation.DVO, "Davao");
		mapOrgStation.put(OrgStation.DPL, "Dipolog");
		mapOrgStation.put(OrgStation.DGT, "Dumaguete");
		mapOrgStation.put(OrgStation.GES, "GeneralSantos");
		mapOrgStation.put(OrgStation.ILO, "Iloilo");
		mapOrgStation.put(OrgStation.KLO, "Kalibo");
		mapOrgStation.put(OrgStation.LAO, "Laoag");
		mapOrgStation.put(OrgStation.LGP, "Legazpi");
		mapOrgStation.put(OrgStation.MNL, "Manila");
		mapOrgStation.put(OrgStation.WNP, "Naga");
		mapOrgStation.put(OrgStation.OZC, "Ozamiz");
		mapOrgStation.put(OrgStation.PAG, "Pagadian");
		mapOrgStation.put(OrgStation.PPS, "PuertoPrincesa");
		mapOrgStation.put(OrgStation.RXS, "Roxas");
		mapOrgStation.put(OrgStation.SJI, "SanJose (Mindoro)");
		mapOrgStation.put(OrgStation.IAO, "Siargao");
		mapOrgStation.put(OrgStation.SUG, "Surigao");
		mapOrgStation.put(OrgStation.TAC, "Tacloban");
		mapOrgStation.put(OrgStation.TDG, "Tandag");
		mapOrgStation.put(OrgStation.TWT, "Tawi-Tawi");
		mapOrgStation.put(OrgStation.TUG, "Tuguegarao");
		mapOrgStation.put(OrgStation.VRC, "Virac");
		mapOrgStation.put(OrgStation.ZAM, "Zamboanga");
		mapOrgStation.put(OrgStation.DOH, "Doha");
		mapOrgStation.put(OrgStation.DMM, "Dammam");
		mapOrgStation.put(OrgStation.RUH, "Riyadh");
		mapOrgStation.put(OrgStation.SIN, "Singapore");
		mapOrgStation.put(OrgStation.PUS, "Busan");
		mapOrgStation.put(OrgStation.ICN, "Seoul (Incheon)");
		mapOrgStation.put(OrgStation.TPE, "Taipei");
		mapOrgStation.put(OrgStation.BKK, "Bangkok");
		mapOrgStation.put(OrgStation.CNX, "ChiangMai");
		mapOrgStation.put(OrgStation.HDY, "HatYai");
		mapOrgStation.put(OrgStation.KBV, "Krabi");
		mapOrgStation.put(OrgStation.HKT, "Phuket");
		mapOrgStation.put(OrgStation.GUM, "Guam");
		mapOrgStation.put(OrgStation.DXB, "Dubai");
		mapOrgStation.put(OrgStation.HAN, "Hanoi");
		mapOrgStation.put(OrgStation.SGN, "HoChiMinh");
	}
	
	public static final Map<OrgStation, String> mapDestStation = mapOrgStation;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
