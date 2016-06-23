package cebu.util.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cebu.model.Ticket;
import cebu.util.common.CommonUtil;

public class HtmlXmlParser_ZE {
	/** 单例模式 **/
	private HtmlXmlParser_ZE() {

	}

	private static class HtmlXmlParser_ZEInstanceHolder {
		private static HtmlXmlParser_ZE HtmlXmlParser = new HtmlXmlParser_ZE();
	}

	public static HtmlXmlParser_ZE getInstance() {
		return HtmlXmlParser_ZEInstanceHolder.HtmlXmlParser;
	}

	public String parseTax(String html) {
		String tax = "";
		if (CommonUtil.checkStrNullOrEmpty(html))
			return tax;

		Document doc = Jsoup.parse(html);
		Element div = doc.select("div.total").last();
		Element li = div.select("li").get(1);

		String str = li.ownText().trim().replace(",", "");
		String currency = str.substring(str.length() - 3, str.length());
		double taxDouble = Double.valueOf(str.replace(currency, ""));

		Element strong = doc.select("div.inner").last().select("strong").last();
		int adultCount = Integer.valueOf(strong.ownText().trim());

		double taxVal = taxDouble / adultCount;
		
		return String.valueOf(taxVal);
	}

	public List<Ticket> parseTicketPartly(String xml) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		if (CommonUtil.checkStrNullOrEmpty(xml))
			return tickets;

		// convert String into InputStream
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		org.w3c.dom.Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("list");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
				org.w3c.dom.Element highFareNode = (org.w3c.dom.Element) eElement.getElementsByTagName("highFare").item(0);
				if (highFareNode.getChildNodes().getLength() == 1) {
					continue;
				}

				String flightNumber = eElement.getElementsByTagName("flightNumber").item(0).getTextContent();
				String currency = highFareNode.getElementsByTagName("currencyCode").item(0).getTextContent();
				
				String adultPrice = highFareNode.getElementsByTagName("amount").item(0).getTextContent();
				String journerKey = eElement.getElementsByTagName("journeyKey").item(0).getTextContent();
				
				String depAirport, arrAirport, depTime, arrTime;
				depAirport = arrAirport = depTime = arrTime = "";
				String carrier = "ZE";
				
				Pattern pattern = Pattern.compile("([A-Z]{3}).+([A-Z]{3})");
				Matcher matcher = pattern.matcher(journerKey);
				while (matcher.find()) {
					depAirport = matcher.group(1);
					arrAirport = matcher.group(2);
				}
				
				pattern = Pattern.compile("(?<month>\\d{2})/(?<day>\\d{2})/(?<year>\\d{4})\\s(?<hour>\\d{2}):(?<minute>\\d{2})");
				matcher = pattern.matcher(journerKey);
				int i = 0;
				while (matcher.find()) {
					i++;
					if (i == 1)
						depTime = matcher.group("year")
									+ matcher.group("month")
									+ matcher.group("day")
									+ matcher.group("hour")
									+ matcher.group("minute");
					if (i == 2)
						arrTime = matcher.group("year")
						+ matcher.group("month")
						+ matcher.group("day")
						+ matcher.group("hour")
						+ matcher.group("minute");
				}
				
				Ticket ticket = new Ticket();
				ticket.setCarrier(carrier);
				ticket.setflightNumber(flightNumber);
				ticket.setdepAirport(depAirport);
				ticket.setarrAirport(arrAirport);
				ticket.setdepTime(depTime);
				ticket.setarrTime(arrTime);
				ticket.setadultPrice(adultPrice);
				ticket.setCurrency(currency);
				tickets.add(ticket);
			}
		}
		return tickets;
	}

}
