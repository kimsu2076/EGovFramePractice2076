<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value='/css/bootstrap/css/bootstrap.min.css'/>">
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/css/bootstrap/js/bootstrap.min.js'/>"></script>

<!-- Bootstrap 5 CSS & JS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
   	$( document).ready( function(){
   		<c:if test="${!empty msg}">
   		alert("${msg}");
   		</c:if>
   		})
	
	function add() {
		location.href = "<c:url value='/mgmt.do'/>";
	}
	function view() {
		location.href = "<c:url value='/view.do'/>";
	}
</script>
</head>

<body>
	<div class="card mb-4">
		<div class="card-header">
			<c:if
				test="${sessionScope.userId == null || sessionScope.userId == ''}">
				<form class="row g-3 align-items-end" method="post"
					action="/login.do">
					<div class="col-md-4">
						<label for="user_id" class="form-label">아이디</label> <select
							class="form-select" id="user_id" name="user_id"
							onchange="setPwd(this.value);">
							<option value="">선택하세요</option>
							<option value="admin">관리자</option>
							<option value="guest">사용자</option>
						</select>
					</div>
					<div class="col-md-4">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control" id="password"
							name="password">
					</div>
					<div class="col-md-4">
						<button type="submit" class="btn btn-primary w-100"
							onclick="return check();">로그인</button>
					</div>
				</form>
			</c:if>

			<c:if
				test="${sessionScope.userId != null && sessionScope.userId != ''}">
				<div class="d-flex justify-content-between align-items-center">
					<div>
						<strong>${sessionScope.userName}</strong>님 환영합니다
					</div>
					<button type="button" class="btn btn-secondary" onclick="out();">로그아웃</button>
				</div>
			</c:if>
		</div>
		<div class="card-body">
			<form class="row g-3 mb-4" action="/search.do">
				<div class="col-md-6">
					<label for="searchName" class="form-label">제목</label> <input
						type="text" class="form-control" id="searchName" name="searchName">
				</div>
				<div class="col-md-6 d-flex align-items-end">
					<button type="submit" class="btn btn-secondary">검색</button>
				</div>
			</form>
			<br>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="table-light">
						<tr>
							<th>게시물번호</th>
							<th>제목</th>
							<th>조회수</th>
							<th>등록자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr>
								<td><a href="javascript:view('${result.idx}');"> <c:out
											value="${result.idx}" />
								</a></td>
								<td><a href="javascript:view('${result.idx}');"> <c:out
											value="${result.title}" />
								</a></td>
								<td><c:out value="${result.count}" /></td>
								<td><c:out value="${result.reply}" /></td>
								<td><c:out value="${result.writerNm}" /></td>
								<td><c:out value="${result.indate}" /></td>
								<%-- <td>
											<fmt:formatDate value="${result.indate}" pattern="yyyy-MM-dd hh:mm:ss" />
											</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="card-footer text-end">
			<c:if test="${!empty sessionScope.userId }">
				<button type="button" class="btn btn-secondary" onclick="add();">등록</button>
			</c:if>
		</div>
	</div>
	</div>
</body>

</html>