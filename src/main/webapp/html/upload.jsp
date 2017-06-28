<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> test file upload </title>  	
</head>


<body>   
    <form  action="../file/upload" method="post"   enctype="multipart/form-data" >		
		<input type="hidden" name="folder" value="folder"><br><br>
	    <input type="file" name="file"><br><br>
	    <input type="submit" name="submit" value="upload">
    </form>
</body>


</html>