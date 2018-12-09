<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.hbase.solr.ConfigData" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<title>修改配置文件</title>
<%
	ConfigData cd = new ConfigData();
	String importIdentifier = cd.getImportIdentifier();
	String incrementiImportDate = cd.getIncrementiImportDate();
	String str1 = cd.getStartRowAndStopRow();
	String[] str = str1.split(",");
	String startRow = str[0];
	String stopRow = str[1];
	String str2 = cd.getstartTimeAndEndTime();
	String[] str3 = str2.split(",");
	String startTime = str3[0];
	String endTime = str3[1];
%>
<script type="text/javascript">
function change() {
    $.ajax({
    //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "changeConf" ,//url
        data: $('#form1').serialize(),
        //data: document.getElementById("form1").serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result == "0") {
                alert("保存成功");
            }else{
            	alert("保存失败");
            }
            ;
        },
        error : function() {
            alert("系统异常！");
        }
    });
}
</script>

</head>
<body>
	<div style="text-align:center;width:80%;margin:0 auto;">
		<div><h1>修改solr配置文件data-config.xml</h1></div>
		<div style="width:400px;margin:0 auto;">
			<span style="font-weight: bold;">目前导入模式分为4种</span><br/>
			<div style="text-align: left;">
				<span style="float:left;"><span style="font-weight: bold;">导入模式0</span>代表全量导入。</span><br/>
				<span style="float:left;"><span style="font-weight: bold;">导入模式1</span>代表根据RowKey中日期进行增量导入日期的格式为yyyyMMdd 如20181111。</span><br/>
				<span style="float:left;"><span style="font-weight: bold;">导入模式2</span>代表根据RowKey的开始与结束进行增量导入， 参数为开始Row与结束Row，其中结果不包含结束Row。</span><br/>
				<span style="float:left;"><span style="font-weight: bold;">导入模式3</span>代表根据开始与结束时间进行增量导入， 开始与结束时间格式为yyyyMMddHHmmss 如20181111230800。</span><br/>
			</div>
		</div>
		<div style="overflow: hidden; clear:both;padding-top:20px;">
			<form  id="form1" action="##" onsubmit="return false" method="post">
				<font>*******************************************************</font>
				<div>导入模式：<input type="text" name="importIdentifier" value="<%= importIdentifier%>"/></div>
				<font>*******************************************************<br/></font>
				<font>根据RowKey中包含的日期进行增量导入<br/></font>
				<div>Row日期：<input type="text" name="incrementiImportDate" value="<%= incrementiImportDate%>"/></div>
				<font>*******************************************************<br/></font>
				<font>根据开始Row与结束Row进行增量导入<br/></font>
				<div>开始Row：<input type="text" name="startRow" value="<%= startRow%>"/></div>
				<div>结束Row：<input type="text" name="stopRow" value="<%= stopRow%>"/></div>
				<font>*******************************************************<br/></font>
				<div>根据开始时间与结束时间进行增量导入<br/></div>
				<div>开始时间：<input type="text" name="startTime" value="<%= startTime%>"/></div>
				<div>结束时间：<input type="text" name="endTime" value="<%= endTime%>"/></div>
				<font>*******************************************************<br/></font>
				<div style="padding-top:20px;"><input style="font-weight: bold;border-color:#388BFF;padding: 5px 7px;border-radius: 5px;" type="button" onclick="change()" value="提交保存"></div>
			</form>
		</div>
	</div>
</body>
</html>