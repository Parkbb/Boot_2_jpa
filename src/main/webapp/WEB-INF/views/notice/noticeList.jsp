<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/BS.jsp" />
<style type="text/css">
.ldBar {
  position: relative;
}
.ldBar.label-center > .ldBar-label {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-shadow: 0 0 3px #fff;
}
.ldBar-label:after {
  content: "%";
  display: inline;
}
.ldBar.no-percent .ldBar-label:after {
  content: "";
}

</style>
</head>
<c:import url="../template/nav.jsp" />
<body>
<div class="contents">
<script type="text/javascript" src="loading-bar.js"></script>
<div class="ldBar" data-value="50">
</div>
	<div>
		<button class="btn btn-danger" onclick="location.href='${board}Write'">Write</button>
	</div>
	${list.totalPages}
	${list.number}
	<%-- ${list.numberOfElements} --%>
	${list.size}
	<%-- ${list.gettotalElements} --%>
	${curBlock}
	${totalBlock}
	<table class="table table-hover">
		<tr>
			<td>NUM</td>
			<td>TITLE</td>
			<td>WRITER</td>
			<td>DATE</td>
			<td>HIT</td>
			<td>FILE</td>
		</tr>
		<c:forEach items="${list.content}" var="vo">
			<tr>
				<td>${vo.num}</td>
				<td style="cursor: pointer;" onclick="location.href='${board}Select?num=${vo.num}';">${vo.title}</td>
				<td>${vo.writer}</td>
				<td>${vo.regDate}</td>
				<td>${vo.hit}</td>
				<td>
				<c:forEach items="${vo.noticeFilesVOs}" var="f">${f.fname}//</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="margin: auto;">
			<ul class="pagination">
				<c:if test="${curBlock gt 1}">
					<li><span class="list" id="${(curBlock-1)*5-1}">이전</span></li>
				</c:if>
				
				<%-- <c:if test="${curBlock le list.totalPages/5}">
					<c:forEach begin="${(curBlock-1)*5}" end="${list.totalPages}" var="i">
					<li><span class="list" id="${i}">${i+1}</span></li>
				</c:forEach>
				</c:if> --%>
				
				<c:choose>
					
					<c:when test="${curBlock eq totalBlock}">
						<%-- <c:forEach begin="${(curBlock-1)*5}" end="${list.totalPages-1}" var="i"> --%>
						<c:forEach begin="${(curBlock-1)*5}" end="${list.totalPages-1}" var="i">
							<li><span class="list" id="${i}">${i+1}</span></li>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<c:forEach begin="${(curBlock-1)*5}" end="${curBlock*5-1}" var="i">
							<li><span class="list" id="${i}">${i+1}</span></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
				<%-- <c:if test="${curBlock ne list.totalPages/5}">
				<c:forEach begin="${(curBlock-1)*5}" end="${curBlock*5-1}" var="i">
					<li><span class="list" id="${i}">${i+1}</span></li>
				</c:forEach>
				</c:if> --%>
				
				
				<c:if test="${curBlock le list.totalPages/5}">
					<li><span class="list" id="${curBlock*5}">다음</span></li>
				</c:if>
			</ul>
		</div>
		
		<form  id="frm" action="noticeList">
			<input type="hidden" id="page" value= "0" name="page" >
			<select name="kind">
				<option id="kt" value="kt">Title</option>
				<option id="kw" value="kw">Writer</option>
				<option id="kc" value="kc">Contents</option>
			</select> 
			<input type="text" name="search" value="${param.search}">
			<button>검색</button>

		</form>
</div>
<script type="text/javascript">



var kind = '${param.kind}';
if(kind==''){
	kind='kt';
}
$("#"+kind).prop("selected", true);

$(".list").click(function() {
	$("#page").val($(this).attr("id"))
	$("#frm").submit();
});

</script>
</body>
</html>