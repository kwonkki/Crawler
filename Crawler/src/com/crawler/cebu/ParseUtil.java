package com.crawler.cebu;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseUtil {
	
	private final static String WHITESPACE_UTF8 = "/u00a0";

	public static void getInfo() {
		String url = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
		String html = CrawlerUtil.getHtmlByUrl(url);
		if (html != null && !"".equals(html)) {
			System.out.println(html);
			Document doc = Jsoup.parse(html);
			String selectStr = "select#ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1";
			Element target = doc.select(selectStr).get(0);
			System.out.println(target.text());
			Elements options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.println(option.text().replace(WHITESPACE_UTF8, ""));
			}

			selectStr = "select#ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1";
			target = doc.select(selectStr).get(0);
			System.out.println(target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.println(option.text().replace(WHITESPACE_UTF8, ""));
			}
		}
	}
	
	/**
	 * 从文件解析html，提供相对应的url
	 * @param filePath 本地html文件路径
	 * @param baseUrl 文件的原url地址，解析相关的链接
	 */
	public static void parseByFileWithUrl(String filePath, String baseUrl) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}
		
		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8", baseUrl);
			String prefix = "select#";

System.out.println("--------------------- day departure ---------------------");
			String selectDayDeparture = prefix + FormUtil.Name_CalendarDayDeparture.replace('$', '_');
			Element target = doc.select(selectDayDeparture).get(0);
System.out.println("target text: " + target.text());
			Elements options = target.select("option");
System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();

System.out.println("--------------------- month departure ---------------------");
			String selectMonthDeparture = prefix + FormUtil.Name_CalendarMonthDeparture.replace('$', '_');
			target = doc.select(selectMonthDeparture).get(0);
System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();

System.out.println("--------------------- day return ---------------------");
			String selectDayReturn = prefix + FormUtil.Name_CalendarDayReturn.replace('$', '_');
			target = doc.select(selectDayReturn).get(0);
System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();

System.out.println("--------------------- month departure ---------------------");
			String selectMonthReturn = prefix + FormUtil.Name_CalendarMonthReturn.replace('$', '_');
			target = doc.select(selectMonthReturn).get(0);
System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();
		} catch (IOException e) {
			System.out.println("parse exeception ...");
			e.printStackTrace();
		}
	}


	/**
	 * 从文件解析html
	 * @param filePath 本地html文件路径
	 */
	public static void parseByFile(String filePath) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}
		
		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8");
			String prefix = "select#";

System.out.println("--------------------- day departure ---------------------");
			String selectDayDeparture = prefix + FormUtil.Name_CalendarDayDeparture.replace('$', '_');
			Element target = doc.select(selectDayDeparture).get(0);
System.out.println("target text: " + target.text());
			Elements options = target.select("option");
System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();

System.out.println("--------------------- month departure ---------------------");
			String selectMonthDeparture = prefix + FormUtil.Name_CalendarMonthDeparture.replace('$', '_');
			target = doc.select(selectMonthDeparture).get(0);
System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();

System.out.println("--------------------- day return ---------------------");
			String selectDayReturn = prefix + FormUtil.Name_CalendarDayReturn.replace('$', '_');
			target = doc.select(selectDayReturn).get(0);
System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();

System.out.println("--------------------- month departure ---------------------");
			String selectMonthReturn = prefix + FormUtil.Name_CalendarMonthReturn.replace('$', '_');
			target = doc.select(selectMonthReturn).get(0);
System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
System.out.println();
		} catch (IOException e) {
			System.out.println("parse exeception ...");
			e.printStackTrace();
		}
	}













}
