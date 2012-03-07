<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />
<link href="/css/map.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/JavaScript"
	src=" http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/JavaScript" src="/js/map.js"></script>
<title>WhoMovedMyCheese</title>
</head>
<body>
	<div id="info">
		<h2>地図画面</h2>
		<p>
			<a href="/index">位置情報を登録</a>
		</p>
		<p>
			<input type="text" id="tag" name="tag"
				value='<c:out value="${tag}" />' /><br /> <input type="button"
				id="search" value="タグで検索" />
		</p>
		<%-- 位置情報を表示 --%>
		<div id="places"></div>
	</div>
	<%-- 地図を表示 --%>
	<div id="map"></div>
</html>