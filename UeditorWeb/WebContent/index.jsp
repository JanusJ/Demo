<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8" src=" utf8-jsp/ueditor.config.js"></script> 
<script type="text/javascript" charset="utf-8" src=" utf8-jsp/ueditor.all.min.js"> </script>
 <script type="text/javascript" charset="utf-8" src=" utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="UeditorServlet" method="post">
	    <h1>完整demo</h1>
		 <!-- 加载编辑器的容器 -->
	    <script id="container" name="content" type="text/plain">
        	这里写你的初始化内容
    	</script>
	    <input type="submit" value="提交"/>
	</form>
</body>
<script type="text/javascript">
<!-- 实例化编辑器 -->
    var ue = UE.getEditor('container',{
    	
    });
</script>
</script>
</html>