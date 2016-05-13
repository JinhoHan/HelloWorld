<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>일반적인 파일 업로드</title>
</head>
<body>

	<!-- 파일 선택, 파일 전송 영역 -->
	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file"><input type="submit">
	</form>
	
</body>
</html>