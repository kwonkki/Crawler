package cebu.util.parser.test;

import java.io.File;

import org.junit.Test;

import cebu.util.common.CommonUtil;
import cebu.util.parser.HtmlParser_NK;


public class HtmlParser_Spirit_Test {

	HtmlParser_NK parser = HtmlParser_NK.getInstance();
	String savePath = "D:/Documents/Github/DataCrawl/Data/save.html";
	
	
	@Test
	public void test_parseTicket() {
		String html = CommonUtil.readStrFromFile(new File(savePath));
		System.out.println(parser.parseTicket(html));
	}

}
