package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.google.gson.JsonArray;

import cebu.model.Ticket;
import cebu.model.TicketPrice;
import cebu.util.crawler.Crawler_5J;

public class JejuTest {
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	private String SavePath = Dir + "post_response_7C.html";
	// 爬虫类和html解析类
	private static Crawler_5J crawler = Crawler_5J.getInstance();
	public static void main(String[] args) {
	}

	
	
	@Test
	public void test_final() throws Exception {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("AdultPaxCnt", "2"));
		formParams.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		formParams.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		formParams.add(new BasicNameValuePair("RouteType", "I"));
		formParams.add(new BasicNameValuePair("SystemType", "IBE"));
		formParams.add(new BasicNameValuePair("Language", "EN"));
		formParams.add(new BasicNameValuePair("DepStn", "WEH"));
		formParams.add(new BasicNameValuePair("ArrStn", "ICN"));
		formParams.add(new BasicNameValuePair("SegType", "DEP"));
		formParams.add(new BasicNameValuePair("TripType", "OW"));
		formParams.add(new BasicNameValuePair("DepDate", "2016-06-30"));
		formParams.add(new BasicNameValuePair("Index", "3"));
		
		String postUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(formEntity);

		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("DNT", "1");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		CloseableHttpResponse response = httpClient.execute(httpPost, context);
		String html = crawler.getHtmlByResponse(response);
		crawler.saveHtmlToFile(html, SavePath);
System.out.println(html);



		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(html);
		
		JSONObject resultObj = (JSONObject) jsonObj.get("Result");
		JSONObject dataObj = (JSONObject) resultObj.get("data");
		JSONArray availDataArr = (JSONArray) dataObj.get("availData");
		for(Object obj : availDataArr) {
			JSONObject flightObj = (JSONObject) obj;
			
			String carrier = (String) flightObj.get("carrier");
			
			String fltNo = (String) flightObj.get("fltNo");
			String flightNumber = carrier + " " + fltNo;
			
			String depAirport = (String) flightObj.get("depStn");
			String arrAirport = (String) flightObj.get("arrStn");
			
			String depDate = (String) flightObj.get("depDate");
			String depHour = (String) flightObj.get("depTime");
			String depTime = depDate + depHour; 
			
			String arrDate = (String) flightObj.get("arrDate");
			String arrHour = (String) flightObj.get("arrTime");
			String arrTime = arrDate + arrHour;
			
			String currency = (String) flightObj.get("currency");
			
			Ticket ticket = new Ticket();
			ticket.setCarrier(carrier);
			ticket.setflightNumber(flightNumber);
			ticket.setdepAirport(depAirport);
			ticket.setarrAirport(arrAirport);
			ticket.setdepTime(depTime);
			ticket.setarrTime(arrTime);
			ticket.setCurrency(currency);
System.out.println("ticket: " + ticket);
		}

	
		List<NameValuePair> formParams2 = new ArrayList<NameValuePair>();
		formParams2.add(new BasicNameValuePair("ReqType", "Price"));
		formParams2.add(new BasicNameValuePair("TripType", "OW"));
		formParams2.add(new BasicNameValuePair("AdultPaxCnt", "2"));
		formParams2.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		formParams2.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		formParams2.add(new BasicNameValuePair("DepStn", "WEH"));
		formParams2.add(new BasicNameValuePair("ArrStn", "ICN"));
		formParams2.add(new BasicNameValuePair("DepDate", "201606301150"));
		formParams2.add(new BasicNameValuePair("ArrDate", "201606301355"));
		formParams2.add(new BasicNameValuePair("FltNo", "8562"));
		formParams2.add(new BasicNameValuePair("RBD", "O"));
		formParams2.add(new BasicNameValuePair("FareBasis", "OOW1CN"));
		formParams2.add(new BasicNameValuePair("DepDate2", ""));
		formParams2.add(new BasicNameValuePair("ArrDate2", ""));
		formParams2.add(new BasicNameValuePair("DepStn2", ""));
		formParams2.add(new BasicNameValuePair("ArrStn2", ""));
		formParams2.add(new BasicNameValuePair("Language", "EN"));
		formParams2.add(new BasicNameValuePair("RouteType", "I"));
		formParams2.add(new BasicNameValuePair("SystemType", "IBE"));
		
		String postUrl2 = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchFare.do";
		UrlEncodedFormEntity formEntity2 = new UrlEncodedFormEntity(formParams2, Consts.UTF_8);
		HttpPost httpPost2 = new HttpPost(postUrl2);
		httpPost2.setEntity(formEntity2);

		httpPost2.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost2.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost2.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpPost2.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost2.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost2.setHeader("Host", "www.jejuair.net");
		httpPost2.setHeader("DNT", "1");
		httpPost2.setHeader("Origin", "https://www.jejuair.net");
		httpPost2.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");
		httpPost2.setHeader("X-Requested-With", "XMLHttpRequest");

		response = httpClient.execute(httpPost2, context);
		html = crawler.getHtmlByResponse(response);
		crawler.saveHtmlToFile(html, SavePath);
System.out.println(html);
	}
	
	
	
	
	
	@Test
	public void test_match() {
		String str = "NetFunnel.gRtype=5101;NetFunnel.gControl.resul" + 
		"t='5002:200:key=45DAA5D8E96606173883AE38FF1B2A887F93A4DB73288CA37BEA56941D957922" + 
		"733F0686F16DAEED0A19C3A53D2A2A668CEF525E248A2F0CBA3300189B88CB4790380560FF1645B3" + 
		"2BFE90D1CA9D4F452F019F599E4CC67FBE08CE04706479216428C47DB38E08B635A222911665CE54" + 
		"2C30&nwait=0&nnext=0&tps=0&ttl=0&ip=apc.jejuair.net&port=443'; NetFunnel.gContro" + 
		"l._showResult();";
		
		
		String regex = "5002:200:.+443";
		//String regex = "NetFunnel";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			System.out.println(matcher.group());
		}

	}
	
	
	
	@Test
	public void test_parse_json() throws Exception {
		String jsonStr = 
				"{\"Result\":{\"message\":\"\",\"data\":{\"availData\":[{\"seatCount\":\"\",\"normalEquivFare\":\"" + 
						"1630\",\"afterDepDate\":\"\",\"nonFlt\":\"\",\"YClassStatusCode\":\"\",\"normalFareRuleInfoDat" + 
						"as\":[{\"fareRuleItem\":\"Fare Type\",\"fareRuleDesc\":\"Regular Fare\"},{\"fareRuleItem\":" + 
						"\"Fee : Reservation Change\",\"fareRuleDesc\":\"CNY 25 per Sector\"},{\"fareRuleItem\":\"" + 
						"Fee : Cancellation\",\"fareRuleDesc\":\"CNY 50 per sector\"},{\"fareRuleItem\":\"Free al" + 
						"lowance baggage\",\"fareRuleDesc\":\"1PC/20KG (INFANT10KG)\"}],\"arrStnDesc\":\"Seoul(In" + 
						"cheon)(ICN)\",\"taxDataIndex\":\"\",\"normalFee\":\"\",\"specialRBD\":\"\",\"carrier\":\"7C\",\"sp" + 
						"ecialEquivFareName\":\"\",\"pointUseRate\":\"\",\"arrTime\":\"1355\",\"acType\":\"737\",\"discou" + 
						"ntSeatCount\":\"8\",\"depStnDesc\":\"Weihai(WEH)\",\"depTime\":\"1150\",\"discountFee\":\"\",\"s" + 
						"eatDesc\":\"\",\"normalSeatCount\":\"9\",\"seatAvailDataBeans\":[{\"seatCount\":\"9\",\"RBD\":\"" + 
						"Y\"},{\"seatCount\":\"9\",\"RBD\":\"B\"},{\"seatCount\":\"9\",\"RBD\":\"K\"},{\"seatCount\":\"9\",\"RB" + 
						"D\":\"N\"},{\"seatCount\":\"9\",\"RBD\":\"Q\"},{\"seatCount\":\"9\",\"RBD\":\"M\"},{\"seatCount\":\"9\"" + 
						",\"RBD\":\"T\"},{\"seatCount\":\"9\",\"RBD\":\"W\"},{\"seatCount\":\"8\",\"RBD\":\"O\"},{\"seatCount\"" + 
						":\"0\",\"RBD\":\"R\"},{\"seatCount\":\"0\",\"RBD\":\"X\"},{\"seatCount\":\"0\",\"RBD\":\"S\"},{\"seatCo" + 
						"unt\":\"0\",\"RBD\":\"L\"},{\"seatCount\":\"0\",\"RBD\":\"H\"},{\"seatCount\":\"0\",\"RBD\":\"E\"},{\"se" + 
						"atCount\":\"0\",\"RBD\":\"J\"},{\"seatCount\":\"0\",\"RBD\":\"F\"},{\"seatCount\":\"0\",\"RBD\":\"V\"}," + 
						"{\"seatCount\":\"0\",\"RBD\":\"G\"},{\"seatCount\":\"9\",\"RBD\":\"Z\"},{\"seatCount\":\"9\",\"RBD\":\"" + 
						"D\"},{\"seatCount\":\"9\",\"RBD\":\"C\"},{\"seatCount\":\"9\",\"RBD\":\"A\"},{\"seatCount\":\"0\",\"RB" + 
						"D\":\"I\"},{\"seatCount\":\"0\",\"RBD\":\"P\"},{\"seatCount\":\"9\",\"RBD\":\"U\"}],\"normalEquivFar" + 
						"eName\":\"Normal fare\",\"specialEquivFareBasis\":\"\",\"specialFee\":\"\",\"fltType\":\"\",\"di" + 
						"scountFareRuleNo\":\"CN25\",\"discountEquivFareBasis\":\"OOW1CN\",\"pointFareInfo\":{\"pnt" + 
						"ClassFareBasis\":\"\",\"pntClassSeatCount\":\"\",\"specialEquivFarePoint\":\"\",\"pntClassRB" + 
						"D\":\"\",\"discountEquivFarePoint\":\"\",\"normalEquivFarePoint\":\"\",\"minFarePoint\":\"\"},\"" + 
						"tax\":\"0\",\"specialFareRuleInfoDatas\":[],\"fltNo\":\"8562\",\"discountEquivFareName\":\"S" + 
						"pecial fare\",\"normalEquivFareBasis\":\"YOW1CN\",\"RBD\":\"\",\"minFare\":\"0\",\"jjimFlag\":\"" + 
						"\",\"depStn\":\"WEH\",\"normalRBD\":\"Y\",\"fareBasis\":\"\",\"fare\":\"\",\"beforeDepDate\":\"\",\"sp" + 
						"ecialEquivTotalFare\":\"\",\"equivFare\":\"\",\"discountFareRuleInfoDatas\":[{\"fareRuleIt" + 
						"em\":\"Fare Type\",\"fareRuleDesc\":\"Special Fare\"},{\"fareRuleItem\":\"Fee : Reservatio" + 
						"n Change\",\"fareRuleDesc\":\"CNY 50 per Sector\"},{\"fareRuleItem\":\"Fee : Cancellatio" + 
						"n\",\"fareRuleDesc\":\"CNY 100 per sector\"},{\"fareRuleItem\":\"Free allowance baggage\"" + 
						",\"fareRuleDesc\":\"1PC/15KG (?? 10KG)\"}],\"currency\":\"CNY\",\"equivFareBasis\":\"\",\"aft" + 
						"erMinFare\":\"0\",\"specialSeatCount\":\"\",\"discountEquivTotalFare\":\"\",\"specialFareRul" + 
						"eNo\":\"\",\"connectAvailData\":{\"normalEquivFare\":\"\",\"depStn\":\"\",\"normalRBD\":\"\",\"cur" + 
						"rency\":\"\",\"arrStnDesc\":\"\",\"specialRBD\":\"\",\"specialEquivFareName\":\"\",\"arrTime\":\"\"" + 
						",\"carrier\":\"\",\"acType\":\"\",\"depStnDesc\":\"\",\"arrDate\":\"\",\"depTime\":\"\",\"depDate\":\"\"" + 
						",\"arrStn\":\"\",\"normalEquivFareName\":\"\",\"specialEquivFareBasis\":\"\",\"fltType\":\"\",\"d" + 
						"iscountEquivFareBasis\":\"\",\"specialEquivFare\":\"\",\"discountEquivFare\":\"\",\"fltNo\":\"" + 
						"\",\"discountEquivFareName\":\"\",\"normalEquivFareBasis\":\"\",\"discountRBD\":\"\"},\"arrDat" + 
						"e\":\"20160630\",\"depDate\":\"20160630\",\"fuelCharge\":\"0\",\"arrStn\":\"ICN\",\"normalFareRu" + 
						"leNo\":\"CN21\",\"fareRuleNo\":\"\",\"specialEquivFare\":\"\",\"beforeMinFare\":\"0\",\"groupFar" + 
						"eDatas\":[],\"normalEquivTotalFare\":\"\",\"connectNo\":\"\",\"discountEquivFare\":\"420\",\"d" + 
						"iscountRBD\":\"O\"},{\"seatCount\":\"\",\"normalEquivFare\":\"1630\",\"afterDepDate\":\"\",\"non" + 
						"Flt\":\"\",\"YClassStatusCode\":\"\",\"normalFareRuleInfoDatas\":[{\"fareRuleItem\":\"Fare T" + 
						"ype\",\"fareRuleDesc\":\"Regular Fare\"},{\"fareRuleItem\":\"Fee : Reservation Change\",\"" + 
						"fareRuleDesc\":\"CNY 25 per Sector\"},{\"fareRuleItem\":\"Fee : Cancellation\",\"fareRul" + 
						"eDesc\":\"CNY 50 per sector\"},{\"fareRuleItem\":\"Free allowance baggage\",\"fareRuleDe" + 
						"sc\":\"1PC/20KG (INFANT10KG)\"}],\"arrStnDesc\":\"Seoul(Incheon)(ICN)\",\"taxDataIndex\":" + 
						"\"\",\"normalFee\":\"\",\"specialRBD\":\"\",\"carrier\":\"7C\",\"specialEquivFareName\":\"\",\"poin" + 
						"tUseRate\":\"\",\"arrTime\":\"1855\",\"acType\":\"737\",\"discountSeatCount\":\"5\",\"depStnDesc" + 
						"\":\"Weihai(WEH)\",\"depTime\":\"1645\",\"discountFee\":\"\",\"seatDesc\":\"\",\"normalSeatCount" + 
						"\":\"9\",\"seatAvailDataBeans\":[{\"seatCount\":\"9\",\"RBD\":\"Y\"},{\"seatCount\":\"9\",\"RBD\":\"" + 
						"B\"},{\"seatCount\":\"9\",\"RBD\":\"K\"},{\"seatCount\":\"9\",\"RBD\":\"N\"},{\"seatCount\":\"9\",\"RB" + 
						"D\":\"Q\"},{\"seatCount\":\"9\",\"RBD\":\"M\"},{\"seatCount\":\"9\",\"RBD\":\"T\"},{\"seatCount\":\"5\"" + 
						",\"RBD\":\"W\"},{\"seatCount\":\"5\",\"RBD\":\"O\"},{\"seatCount\":\"0\",\"RBD\":\"R\"},{\"seatCount\"" + 
						":\"0\",\"RBD\":\"X\"},{\"seatCount\":\"0\",\"RBD\":\"S\"},{\"seatCount\":\"0\",\"RBD\":\"L\"},{\"seatCo" + 
						"unt\":\"0\",\"RBD\":\"H\"},{\"seatCount\":\"0\",\"RBD\":\"E\"},{\"seatCount\":\"0\",\"RBD\":\"J\"},{\"se" + 
						"atCount\":\"0\",\"RBD\":\"F\"},{\"seatCount\":\"0\",\"RBD\":\"V\"},{\"seatCount\":\"0\",\"RBD\":\"G\"}," + 
						"{\"seatCount\":\"9\",\"RBD\":\"Z\"},{\"seatCount\":\"9\",\"RBD\":\"D\"},{\"seatCount\":\"0\",\"RBD\":\"" + 
						"C\"},{\"seatCount\":\"0\",\"RBD\":\"A\"},{\"seatCount\":\"0\",\"RBD\":\"I\"},{\"seatCount\":\"0\",\"RB" + 
						"D\":\"P\"},{\"seatCount\":\"9\",\"RBD\":\"U\"}],\"normalEquivFareName\":\"Normal fare\",\"specia" + 
						"lEquivFareBasis\":\"\",\"specialFee\":\"\",\"fltType\":\"\",\"discountFareRuleNo\":\"CN25\",\"di" + 
						"scountEquivFareBasis\":\"OOW1CN\",\"pointFareInfo\":{\"pntClassFareBasis\":\"\",\"pntClass" + 
						"SeatCount\":\"\",\"specialEquivFarePoint\":\"\",\"pntClassRBD\":\"\",\"discountEquivFarePoin" + 
						"t\":\"\",\"normalEquivFarePoint\":\"\",\"minFarePoint\":\"\"},\"tax\":\"0\",\"specialFareRuleInf" + 
						"oDatas\":[],\"fltNo\":\"8504\",\"discountEquivFareName\":\"Special fare\",\"normalEquivFar" + 
						"eBasis\":\"YOW1CN\",\"RBD\":\"\",\"minFare\":\"0\",\"jjimFlag\":\"\",\"depStn\":\"WEH\",\"normalRBD\"" + 
						":\"Y\",\"fareBasis\":\"\",\"fare\":\"\",\"beforeDepDate\":\"\",\"specialEquivTotalFare\":\"\",\"equ" + 
						"ivFare\":\"\",\"discountFareRuleInfoDatas\":[{\"fareRuleItem\":\"Fare Type\",\"fareRuleDes" + 
						"c\":\"Special Fare\"},{\"fareRuleItem\":\"Fee : Reservation Change\",\"fareRuleDesc\":\"CN" + 
						"Y 50 per Sector\"},{\"fareRuleItem\":\"Fee : Cancellation\",\"fareRuleDesc\":\"CNY 100 p" + 
						"er sector\"},{\"fareRuleItem\":\"Free allowance baggage\",\"fareRuleDesc\":\"1PC/15KG (?" + 
						"? 10KG)\"}],\"currency\":\"CNY\",\"equivFareBasis\":\"\",\"afterMinFare\":\"0\",\"specialSeatC" + 
						"ount\":\"\",\"discountEquivTotalFare\":\"\",\"specialFareRuleNo\":\"\",\"connectAvailData\":{" + 
						"\"normalEquivFare\":\"\",\"depStn\":\"\",\"normalRBD\":\"\",\"currency\":\"\",\"arrStnDesc\":\"\",\"s" + 
						"pecialRBD\":\"\",\"specialEquivFareName\":\"\",\"arrTime\":\"\",\"carrier\":\"\",\"acType\":\"\",\"d" + 
						"epStnDesc\":\"\",\"arrDate\":\"\",\"depTime\":\"\",\"depDate\":\"\",\"arrStn\":\"\",\"normalEquivFar" + 
						"eName\":\"\",\"specialEquivFareBasis\":\"\",\"fltType\":\"\",\"discountEquivFareBasis\":\"\",\"s" + 
						"pecialEquivFare\":\"\",\"discountEquivFare\":\"\",\"fltNo\":\"\",\"discountEquivFareName\":\"\"" + 
						",\"normalEquivFareBasis\":\"\",\"discountRBD\":\"\"},\"arrDate\":\"20160630\",\"depDate\":\"201" + 
						"60630\",\"fuelCharge\":\"0\",\"arrStn\":\"ICN\",\"normalFareRuleNo\":\"CN21\",\"fareRuleNo\":\"\"" + 
						",\"specialEquivFare\":\"\",\"beforeMinFare\":\"0\",\"groupFareDatas\":[],\"normalEquivTotal" + 
						"Fare\":\"\",\"connectNo\":\"\",\"discountEquivFare\":\"420\",\"discountRBD\":\"O\"}],\"index\":\"3" + 
						"\",\"depDate\":\"20160630\",\"segType\":\"DEP\"},\"code\":\"0000\"}}";	
		
		
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonStr);

		JSONObject resultObj = (JSONObject) jsonObj.get("Result");
		JSONObject dataObj = (JSONObject) resultObj.get("data");
		JSONArray availDataArr = (JSONArray) dataObj.get("availData");
		for(Object obj : availDataArr) {
			JSONObject flightObj = (JSONObject) obj;
			
			String carrier = (String) flightObj.get("carrier");
			
			String fltNo = (String) flightObj.get("fltNo");
			String flightNumber = carrier + " " + fltNo;
			
			String depAirport = (String) flightObj.get("depStn");
			String arrAirport = (String) flightObj.get("arrStn");
			
			String depDate = (String) flightObj.get("depDate");
			String depHour = (String) flightObj.get("depTime");
			String depTime = depDate + depHour; 
			
			String arrDate = (String) flightObj.get("arrDate");
			String arrHour = (String) flightObj.get("arrTime");
			String arrTime = arrDate + arrHour;
			
			String currency = (String) flightObj.get("currency");
			
			Ticket ticket = new Ticket();
			ticket.setCarrier(carrier);
			ticket.setflightNumber(flightNumber);
			ticket.setdepAirport(depAirport);
			ticket.setarrAirport(arrAirport);
			ticket.setdepTime(depTime);
			ticket.setarrTime(arrTime);
			ticket.setCurrency(currency);
System.out.println("ticket: " + ticket);
		}

		
	}
	
	@Test
	public void test_parse_json_price() throws Exception {
		String jsonStr = "{\"Result\":{\"message\":\"\",\"data\":[{\"surCharge\":\"0\",\"farePriceSegDataBeans\":[{\"arrDate\":\"20160630\",\"depTime\":\"1150\",\"depDate\":\"20160630\",\"depStn\":\"WEH\",\"arrStn\":\"ICN\",\"fareBasis\":\"OOW1CN\",\"tripType\":\"OW\",\"surcharge\":\"0.00\",\"ruleRef2\":\"412cn25ehnadtn0331000100sital3\",\"ruleRef1\":\"gepy01(user(bjs,<>,''7c'',n,dept(t01,<>),''jjair/8res6j'',<>,<>),pf2(y,[(agency(bjs300),iatanum(<>),n)],[],[],[],n,<>),<>)\",\"paxType\":\"ADT\",\"msgIdx\":\"69629706\",\"ruleNo\":\"CN25\",\"fltNo\":\"8562\",\"arrTime\":\"1355\",\"RBD\":\"O\"}],\"pointEquivFare\":\"\",\"notValidAfter\":\"\",\"equivFare\":\"420\",\"paxType\":\"ADT\",\"taxAmount\":\"440.00\",\"msgIdx\":\"69629706\",\"totalFare\":\"860.00\",\"readType\":\"C\",\"endorsements\":\"\",\"equivCurrency\":\"CNY\",\"paymentCurrency\":\"\",\"paymentFare\":\"\",\"pointUseRate\":\"\",\"farePriceTaxDataBeans\":[{\"msgIdx\":\"69629706\",\"paymentTaxAmount\":\"\",\"taxAmount\":\"90.00\",\"fareTaxableLocation\":[],\"paymentTaxCurrency\":\"\",\"taxCurrency\":\"CNY\",\"taxCode\":\"CN\",\"taxCountryCode\":\"\"},{\"msgIdx\":\"69629706\",\"paymentTaxAmount\":\"\",\"taxAmount\":\"350.00\",\"fareTaxableLocation\":[],\"paymentTaxCurrency\":\"\",\"taxCurrency\":\"CNY\",\"taxCode\":\"YQ\",\"taxCountryCode\":\"\"}],\"fareCal\":\"weh 7c sel64.47nuc64.47end roe6.514200\",\"notValidBefore\":\"\"},{\"surCharge\":\"0\",\"farePriceSegDataBeans\":[{\"arrDate\":\"20160630\",\"depTime\":\"1150\",\"depDate\":\"20160630\",\"depStn\":\"WEH\",\"arrStn\":\"ICN\",\"fareBasis\":\"OOW1CN\",\"tripType\":\"OW\",\"surcharge\":\"0.00\",\"ruleRef2\":\"412cn25ehncnnn0331000100sital3cat/1900037900\",\"ruleRef1\":\"gepy01(user(bjs,<>,''7c'',n,dept(t01,<>),''jjair/8res6j'',<>,<>),pf2(y,[(agency(bjs300),iatanum(<>),n)],[],[],[],n,<>),<>)\",\"paxType\":\"CNN\",\"msgIdx\":\"69629707\",\"ruleNo\":\"CN25\",\"fltNo\":\"8562\",\"arrTime\":\"1355\",\"RBD\":\"O\"}],\"pointEquivFare\":\"\",\"notValidAfter\":\"\",\"equivFare\":\"320\",\"paxType\":\"CNN\",\"taxAmount\":\"350.00\",\"msgIdx\":\"69629707\",\"totalFare\":\"670.00\",\"readType\":\"C\",\"endorsements\":\"\",\"equivCurrency\":\"CNY\",\"paymentCurrency\":\"\",\"paymentFare\":\"\",\"pointUseRate\":\"\",\"farePriceTaxDataBeans\":[{\"msgIdx\":\"69629707\",\"paymentTaxAmount\":\"\",\"taxAmount\":\"350.00\",\"fareTaxableLocation\":[],\"paymentTaxCurrency\":\"\",\"taxCurrency\":\"CNY\",\"taxCode\":\"YQ\",\"taxCountryCode\":\"\"}],\"fareCal\":\"weh 7c sel48.35nuc48.35end roe6.514200\",\"notValidBefore\":\"\"},{\"surCharge\":\"0\",\"farePriceSegDataBeans\":[{\"arrDate\":\"20160630\",\"depTime\":\"1150\",\"depDate\":\"20160630\",\"depStn\":\"WEH\",\"arrStn\":\"ICN\",\"fareBasis\":\"OOW1CN\",\"tripType\":\"OW\",\"surcharge\":\"0.00\",\"ruleRef2\":\"412cn25ehninfn0331000100sital3cat/1900021419\",\"ruleRef1\":\"gepy01(user(bjs,<>,''7c'',n,dept(t01,<>),''jjair/8res6j'',<>,<>),pf2(y,[(agency(bjs300),iatanum(<>),n)],[],[],[],n,<>),<>)\",\"paxType\":\"INF\",\"msgIdx\":\"69629708\",\"ruleNo\":\"CN25\",\"fltNo\":\"8562\",\"arrTime\":\"1355\",\"RBD\":\"O\"}],\"pointEquivFare\":\"\",\"notValidAfter\":\"\",\"equivFare\":\"50\",\"paxType\":\"INF\",\"taxAmount\":\"0.00\",\"msgIdx\":\"69629708\",\"totalFare\":\"50.00\",\"readType\":\"C\",\"endorsements\":\"\",\"equivCurrency\":\"CNY\",\"paymentCurrency\":\"\",\"paymentFare\":\"\",\"pointUseRate\":\"\",\"farePriceTaxDataBeans\":[],\"fareCal\":\"weh 7c sel6.44nuc6.44end roe6.514200\",\"notValidBefore\":\"\"}],\"code\":\"0000\"}}";
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonStr);

		JSONObject resultObj = (JSONObject) jsonObj.get("Result");
		JSONArray dataObj = (JSONArray) resultObj.get("data");
		JSONObject priceObj = (JSONObject) dataObj.get(0);

		String adultPrice = (String) priceObj.get("equivFare");
		String adultTax = (String) priceObj.get("taxAmount");
		String currency = (String) priceObj.get("equivCurrency");


		TicketPrice ticketPrice = new TicketPrice();
		ticketPrice.setAdultPrice(Double.valueOf(adultPrice.trim()).intValue());
		ticketPrice.setAdultTax(Double.valueOf(adultTax.trim()).intValue());
		ticketPrice.setCurrency(currency);
		
		System.out.println(ticketPrice);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
