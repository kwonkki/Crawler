package com.crawler.cebu;

import java.util.ArrayList;
import java.util.Map;

public class MyUtil {
	public static void printFlightInfo(ArrayList<Map<String, String>> flightList) {
		int size = flightList.size();
		for(int i = 0; i < size; i++) {
			Map<String, String> flightInfo = flightList.get(i);
			for(Map.Entry<String, String> entry : flightInfo.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			System.out.println();
		}
	}
}
