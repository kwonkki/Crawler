package cebu.util.parser.test;

import java.io.File;

import org.junit.Test;

import cebu.util.common.CommonUtil;
import cebu.util.parser.HtmlParser_Spirit;
import cebu.util.parser.HtmlXmlParser_ZE;


public class HtmlXmlParser_ZE_Test {

	HtmlXmlParser_ZE parser = HtmlXmlParser_ZE.getInstance();
	String savePath = "D:/Documents/Github/Crawler/Data/post_response_ZE.xml";
	
	
	@Test
	public void test_parseTicket() {
		String xml = CommonUtil.readHtmlFromFile(new File(savePath));
		System.out.println(parser.parseTicketPartly(xml));
	}

}
