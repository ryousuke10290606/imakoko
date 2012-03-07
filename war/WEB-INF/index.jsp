<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" />
<link rel="stylesheet" href="/css/index.css" type="text/css" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript"
	src="http://code.google.com/apis/gears/gears_init.js"></script>
<title>WhoMovedMyCheese</title>
</head>
<body>
	<div id="container">
		<form action="/index" method="post">
			<h1>現在地登録</h1>
			<dl>
				<dt>
					名前：
					<c:if test="${error_nickname}">
						<span class="error">名前は必須です</span>
					</c:if>
				</dt>
				<dd>
					<input type="text" name="nickname"
						value='<c:out value="${nickname}" />' />
				</dd>
				<dt>タグ：</dt>
				<dd>
					<input type="text" id="tag" name="tag"
						value='<c:out value="${tag}" />' />
				</dd>
				<dt>メッセージ：</dt>
				<dd>
					<input type="text" name="message"
						value="<c:out value="${message}" />" " />
				</dd>
			</dl>
			<p>
				<input type="button" id="regist" value="登録する" />
			</p>
			<p>
				<input type="button" id="map" value="地図を表示する" />
			</p>
			<input type="hidden" name="lat" id="lat" /> <input type="hidden"
				name="lng" id="lng" />
		</form>
	</div>
</body>
</html>