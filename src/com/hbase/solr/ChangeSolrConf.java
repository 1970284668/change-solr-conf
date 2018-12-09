package com.hbase.solr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Servlet implementation class ChangeSolrConf
 */
@WebServlet("/ChangeSolrConf")
public class ChangeSolrConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeSolrConf() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String importIdentifier = request.getParameter("importIdentifier");//导入模式
		PrintWriter out = response.getWriter();
		if("".equals(importIdentifier) || importIdentifier == null) {
			out.print("1");
		}else {
			String incrementiImportDate = request.getParameter("incrementiImportDate");
			if("".equals(incrementiImportDate) || incrementiImportDate == null) {
				incrementiImportDate = " ";
			}
			String startRow = request.getParameter("startRow");
			if("".equals(startRow) || startRow == null) {
				startRow = " ";
			}
			String stopRow = request.getParameter("stopRow");
			if("".equals(stopRow) || stopRow == null) {
				stopRow = " ";
			}
			String startTime = request.getParameter("startTime");
			if("".equals(startTime) || startTime == null) {
				startTime = " ";
			}
			String endTime = request.getParameter("endTime");
			if("".equals(endTime) || endTime == null) {
				endTime = " ";
			}
			
			//String fileName = "D:/eclipse-workspace/hbase-solr/apache-tomcat-8.5.35/webapps/change-solr-conf/WEB-INF/classes/data-config.xml";
			String fileName = "/tmp/data-config.xml";
			String singNode = "/dataConfig/document/entity";
			File file = new File(fileName);
			SAXReader reader = new SAXReader();
			try {
				Document doc = reader.read(file);
				//获取根目录
				Element root = doc.getRootElement(); 
				//获取指定节点
				Element node = (Element) root.selectSingleNode(singNode);
				
				Attribute attribute = node.attribute("importIdentifier");//获取属性
				attribute.setValue(importIdentifier);//设置属性值
				
				Attribute attribute1 = node.attribute("incrementiImportDate");
				attribute1.setValue(incrementiImportDate);
				
				Attribute attribute2 = node.attribute("startRow");
				attribute2.setValue(startRow);
				Attribute attribute3 = node.attribute("stopRow");
				attribute3.setValue(stopRow);
				
				Attribute attribute4 = node.attribute("startTime");
				attribute4.setValue(startTime);
				Attribute attribute5 = node.attribute("endTime");
				attribute5.setValue(endTime);
				//格式化为缩进格式
				OutputFormat format = OutputFormat.createPrettyPrint();
				//设置编码格式
				format.setEncoding("UTF-8");
				try {
					XMLWriter writer = new XMLWriter(new FileWriter(file),format);
					//写入数据
					writer.write(doc);
					writer.close();
					out.print("0");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.close();
	}

}
