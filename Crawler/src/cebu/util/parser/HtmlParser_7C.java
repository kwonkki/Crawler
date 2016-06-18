package cebu.util.parser;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import cebu.model.Ticket;
import cebu.model.TicketPrice;
import cebu.util.common.CommonUtil;

/**
 * 解析类
 * 7C
 * @author Administrator
 *
 */


public class HtmlParser_7C {
	
	/** 单例模式 **/
	private HtmlParser_7C() {

	}

	private static class HtmlParser_7CInstanceHolder {
		private static HtmlParser_7C HtmlParser_7C = new HtmlParser_7C();
	}

	public static HtmlParser_7C getInstance() {
		return HtmlParser_7CInstanceHolder.HtmlParser_7C;
	}
	
	/**
	 * 从json解析价格信息
	 * @param jsonStr
	 * @return 返回TicketPrice类
	 */
	public TicketPrice parseTicketPrice(String jsonStr) {
		if (CommonUtil.checkStrNullOrEmpty(jsonStr)) {
			CommonUtil.log(HtmlParser_7C.class, "json 字符串为空");
			return null;
		}

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParser.parse(jsonStr);
		} catch (ParseException e) {
			CommonUtil.log(HtmlParser_7C.class, "解析json失败");
		}

		JSONObject resultObj = (JSONObject) jsonObj.get("Result");
		JSONArray dataObj = (JSONArray) resultObj.get("data");
		JSONObject priceObj = (JSONObject) dataObj.get(0);

		String adultPrice = (String) priceObj.get("equivFare");	// 价格
		String adultTax = (String) priceObj.get("taxAmount");	// 税
		String currency = (String) priceObj.get("equivCurrency");	// 货币

		TicketPrice ticketPrice = new TicketPrice();
		ticketPrice.setAdultPrice(Double.valueOf(adultPrice.trim()).intValue());
		ticketPrice.setAdultTax(Double.valueOf(adultTax.trim()).intValue());
		ticketPrice.setCurrency(currency);
		
		return ticketPrice;
	}
	
	/**
	 * 从json解析Ticket信息，不包括价格信息
	 * @param jsonStr
	 * @return 返回Ticket列表
	 */
	public ArrayList<Ticket> parseTicketPartly(String jsonStr) {
		if (CommonUtil.checkStrNullOrEmpty(jsonStr)) {
			CommonUtil.log(HtmlParser_7C.class, "json 字符串为空");
			return null;
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParser.parse(jsonStr);
		} catch (ParseException e) {
			CommonUtil.log(HtmlParser_7C.class, "解析json出错");
		}

		JSONObject resultObj = (JSONObject) jsonObj.get("Result");
		JSONObject dataObj = (JSONObject) resultObj.get("data");
		JSONArray availDataArr = (JSONArray) dataObj.get("availData");
		
		// 多个航班
		ArrayList<Ticket> tickets = new ArrayList<Ticket>(availDataArr.size());
		
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
			
			tickets.add(ticket);
		}
		return tickets;
	}
	
}
