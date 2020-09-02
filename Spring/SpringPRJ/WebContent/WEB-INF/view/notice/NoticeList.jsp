<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.NoticeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%
	session.setAttribute("SESSION_USER_ID", "USER01");
	List<NoticeDTO> rList = (List<NoticeDTO>)request.getAttribute("rList");
	
	if(rList==null){
		rList = new ArrayList<NoticeDTO>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공지 리스트</title>
<script type="text/javascript">

function doDetail(seq){
	location.href="/notice/NoticeInfo.do?nSeq=" + seq;
}
</script>
</head>
<body>
<h2>공지사항</h2>
<hr />
<br />
<table border="1" width="60px">
<tr>
	<td width="100" align="center">순번</td>
	<td width="200" align="center">제목</td>
	<td width="100" align="center">조회수</td>
	<td width="100" align="center">등록자</td>
	<td width="100" align="center">등록일</td>
</tr>
<%
for(int i=0; i<rList.size(); i++){
	NoticeDTO rDTO = rList.get(i);
	
	if(rDTO==null){
		rDTO = new NoticeDTO();
	}

%>
<tr>
	<td align="center">
	<%
	
	if(CmmUtil.nvl(rDTO.getNotice_yn()).equals("1")){
		out.print("<b>[공지]</b>");
	}
	%></td>
	<td align="center">
		<a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getNotice_seq())%>');">
		<%=CmmUtil.nvl(rDTO.getTitle()) %></a>
	</td>
	<td align="center"><%=CmmUtil.nvl(rDTO.getRead_cnt()) %></td>
	<td align="center"><%=CmmUtil.nvl(rDTO.getUser_name()) %></td>
	<td align="center"><%=CmmUtil.nvl(rDTO.getReg_dt()) %></td>
</tr>
<%
}
%>
</table>
<a href="/notice/NoticeReg.do">[글쓰기]</a>
</body>
</html>