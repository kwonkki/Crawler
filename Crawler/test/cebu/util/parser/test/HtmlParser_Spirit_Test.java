package cebu.util.parser.test;

import java.io.File;

import org.junit.Test;

import cebu.util.common.CommonUtil;
import cebu.util.parser.HtmlParser_Spirit;


public class HtmlParser_Spirit_Test {

	HtmlParser_Spirit parser = HtmlParser_Spirit.getInstance();
	String savePath = "D:/Documents/Github/DataCrawl/Data/save.html";
	
	
	@Test
	public void test_parseTicket() {
		String html = CommonUtil.readHtmlFromFile(new File(savePath));
		System.out.println(parser.parseTicket(html));
	}

}
