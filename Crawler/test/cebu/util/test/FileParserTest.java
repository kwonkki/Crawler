package cebu.util.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import cebu.model.Ticket;
import cebu.util.FileParser;
import cebu.util.ToolUtil;

public class FileParserTest {

	/**中文测试**/
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String Response_Base_Url = "https://book.cebupacificair.com/Select.aspx";
	private final static String SAVE_PATH_Response_Params = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_params.html";
	private final static String SAVE_PATH_Response_Radio_ = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_radio_.html";
	private final static String SAVE_PATH_IniFile = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/Book a Trip.html";
	
	private static FileParser fileParser = FileParser.getInstance();
	
	
	@Test
	public void test_parseTimeWithUrl() {
		System.out.println(fileParser.parseTimeWithUrl(new File(SAVE_PATH_IniFile), URL));
		System.out.println("done");
	}
	
	@Test
	public void test_parseTime() {
		System.out.println(fileParser.parseTime(new File(SAVE_PATH_IniFile)));
		System.out.println("done");
	}
	
	@Test 
	public void test_parseStation() {
		ToolUtil.printMap(fileParser.parseStation(new File(SAVE_PATH_IniFile)));
		System.out.println("done");
	}
	
	@Test 
	public void test_parseStationWithUrl() {
		ToolUtil.printMap(fileParser.parseStationWithUrl(new File(SAVE_PATH_IniFile), URL));
		System.out.println("done");
	}
	
	@Test
	public void test_parseTicket() {
		File file = new File(SAVE_PATH_Response_Params);
		ArrayList<File> priceFiles = new ArrayList<File>();
		for(int i = 1; i <= 5; i++) {
			priceFiles.add(new File(SAVE_PATH_Response_Radio_.replace(".html", i + ".html")));
		}
		
		ArrayList<Ticket> tickets = fileParser.parseTicket(file, priceFiles);
		for(Ticket ticket : tickets)
			System.out.println(ticket);
	}
	
	@Test
	public void test_parseTicketWithUrl() {
		File file = new File(SAVE_PATH_Response_Params);
		ArrayList<File> priceFiles = new ArrayList<File>();
		for(int i = 1; i <= 5; i++) {
			priceFiles.add(new File(SAVE_PATH_Response_Radio_.replace(".html", i + ".html")));
		}
		
		ArrayList<Ticket> tickets = fileParser.parseTicketWithUrl(file, priceFiles, Response_Base_Url);
		for(Ticket ticket : tickets)
			System.out.println(ticket);
	}
	
	@Test
	public void test_Time() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(sdf.format(new Date()));
	}
	
}
