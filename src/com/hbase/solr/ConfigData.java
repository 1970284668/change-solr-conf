package com.hbase.solr;

import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigData {
	private static Element node = null ;
	private final static String fileName = "/tmp/data-config.xml";
	private final static String singNode = "/dataConfig/document/entity";
	public ConfigData() {
		File file = new File(fileName);
		
		 SAXReader reader = new SAXReader();
		 try {
//			String path =  this.getClass().getResource("/").getPath();
			Document doc = reader.read(file);
			//获取根目录
			Element root = doc.getRootElement();
			this.node = (Element) root.selectSingleNode(singNode);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String  getImportIdentifier() {
		//获取属性
		Attribute attribute = node.attribute("importIdentifier");
		return attribute.getText();
	}
	public static String getIncrementiImportDate() {
		//获取属性
		Attribute attribute = node.attribute("incrementiImportDate");
		String incrementiImportDate = attribute.getText();
		if("".equals(incrementiImportDate) || incrementiImportDate == null) {
			incrementiImportDate = " ";
		}
		return incrementiImportDate;
	}
	
	public static String getStartRowAndStopRow() {
		Attribute attribute = node.attribute("startRow");
		String startRow = attribute.getText();
		if("".equals(startRow) || startRow == null) {
			startRow = " ";
		}
		Attribute attribute1 = node.attribute("stopRow");
		String stopRow = attribute1.getText();
		if("".equals(stopRow) || stopRow == null) {
			stopRow = " ";
		}
		return startRow+","+stopRow;
	}
	
	public static String getstartTimeAndEndTime() {
		Attribute attribute = node.attribute("startTime");
		String startTime = attribute.getText();
		if("".equals(startTime) || startTime == null) {
			startTime = " ";
		}
		Attribute attribute1 = node.attribute("endTime");
		String endTime = attribute1.getText();
		if("".equals(endTime) || endTime == null) {
			endTime = " ";
		}
		return startTime+","+endTime;
	}
	
}
