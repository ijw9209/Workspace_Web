<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<c:catch var="err">
	<c:set var="weather" value="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=${code }"/>
	<c:import url="${weather }" var="weatherimport"/>
	<x:parse var="wa" xml="${weatherimport }"/>
{"pubDate":"<x:out select="$wa/rss/channel/pubDate"/>",
"temp":"<x:out select="$wa/rss/channel/item/description/body/data/temp"/>",
"reh":"<x:out select="$wa/rss/channel/item/description/body/data/reh"/>",	
"pop":"<x:out select="$wa/rss/channel/item/description/body/data/pop"/>",
"x":"<x:out select="$wa/rss/channel/item/description/header/x"/>",
"y":"<x:out select="$wa/rss/channel/item/description/header/y"/>",
"wfKor":"<x:out select="$wa/rss/channel/item/description/body/data/wfKor"/>" }	
</c:catch>
<c:if test="${err != null}">
${err }
</c:if>