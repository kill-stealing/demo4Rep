<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    
    <title>在线水果超市</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<title>无标题文档</title>
<style type="text/css">
<!--
*{
padding:0;
margin:0;
  
}
 
.leftD {
	font-family: "华文楷体";
	font-size: 16px;
	color: #4F3760;
	letter-spacing: 0.2em;
	height: 120px;
	width: 100%;
	padding-top: 20px;
	padding-left:0px;
	line-height: 2em;
}
.title {
	font-family: "华文彩云";
	font-size: 20px;
	letter-spacing: 0.5em;
	text-indent: 20px;

}

a{
text-decoration:none;
}

.dt{
font-size: 16px;
text-align: center;
 
}
#i18N{
  text-align: center;
  width: 80%;
  height: 40px;
  padding-top: 10px;
  background-color: #FFFFFF;
  margin-left: 10px;
  font-size: 18px;
}
ul{
   padding-left: 20px;
   list-style-type: none;
}
.gg{
text-indent: 1em;
line-height: 2em;

}
.STYLE10 {font-family: "宋体"}
.STYLE11 {
	font-family: "华文楷体";
	color: #FF6633;
	font-size: xx-large;
}
.STYLE12 {
	font-family: "宋体";
	color: #FF6633;
	font-size: xx-large;
}
.STYLE14 {
	text-indent: 1em;
	line-height: 2em;
	font-family: "宋体";
	font-weight: bold;
	color: #3333FF;
}
.STYLE15 {color: #3333CC}
.STYLE16 {font-size: 16px}
.STYLE17 {font-size: larger}
-->
</style>



<script type="text/javascript" >
//记得加载jquery
//作者：yanue
//使用参数：1.标题，2.链接地址，3.内容简介
window.onload=function(){
		var pop=new Pop("这里是标题，哈哈",
		"http://www.yanue.info/js/pop/pop.html",
		"请输入你的内容简介，这里是内容简介.请输入你的内容简介，这里是内容简介.请输入你的内容简介，这里是内容简介");
		pop.showDiv();
	}
</script>

<script src="jquery.min.js" type="text/javascript" ></script>
<script src="yanue.pop.js" type="text/javascript" ></script>
</head>

  
<body bgcolor="#FFFFFF">
<div align="center">
  
</div>
<!-- demo -->
<div id="pop" style="display:none;">
	<style type="text/css">
	*{margin:0;padding:0;}
	#pop{background:#fff;width:260px;border:1px solid #e0e0e0;font-size:12px;position: fixed;right:10px;bottom:10px;}
	#popHead{line-height:32px;background:#f6f0f3;border-bottom:1px solid #e0e0e0;position:relative;font-size:12px;padding:0 0 0 10px;}
	#popHead h2{font-size:14px;color:#666;line-height:32px;height:32px;}
	#popHead #popClose{position:absolute;right:10px;top:1px;}
	#popHead a#popClose:hover{color:#f00;cursor:pointer;}
	#popContent{padding:5px 10px;}
	#popTitle a{line-height:24px;font-size:14px;font-family:'微软雅黑';color:#333;font-weight:bold;text-decoration:none;}
	#popTitle a:hover{color:#f60;}
	#popIntro{text-indent:24px;line-height:160%;margin:5px 0;color:#666;}
	#popMore{text-align:right;border-top:1px dotted #ccc;line-height:24px;margin:8px 0 0 0;}
	#popMore a{color:#f60;}
	#popMore a:hover{color:#f00;}
	</style>
	<div id="popHead">
	<a id="popClose" title="关闭">关闭</a>
	<h2>温馨提示</h2>
	</div>
	<div id="popContent">
	<dl>
		<dt id="popTitle"><a href="http://yanue.info/" target="_blank">这里是参数</a></dt>
		<dd id="popIntro">这里是内容简介</dd>
	</dl>
	<p id="popMore"><a href="http://yanue.info/" target="_blank">查看 »</a></p>
	</div>
</div>


  </body>
  </html>
  