<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap/css/bootstrap.min.css">
<script src="/js/jquery.min.js"></script>
<script src="/css/bootstrap/js/bootstrap.min.js"></script>

<!-- Bootstrap 5 CSS & JS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	function add() {
		location.href = "<c:url value='/mgmt.do'/>";
	}
	function view() {
		location.href = "<c:url value='/view.do'/>";
	}
	function setPwd(user_id) {
		if (user_id == "admin") {
			$("#password").val("1234")
		} else if (user_id == "guest") {
			$("#password").val("1234")
		} else {
			$("#password").val("1234")
		}
	}
	function check() {
		if ($("#user_id").val() == "") {
			alert("아이디를 입력하세요");
			return false;
		}
		if ($("#password").val() == "") {
			alert("비밀번호를 입력하세요");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="container my-4">
		<h1 class="mb-4">메인화면</h1>
		<br>
		<div class="card-header">
			<c:if
				test="${sessionScope.userId == null || sessionScope.userId == ''}">
				<form class="row g-3 align-items-end" method="get"
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
		<br>
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
							<th>#</th>
							<th>게시물목록</th>
							<th>제목</th>
							<th>조회수</th>
							<th>등록자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="javascript:view();">1</a></td>
							<td><a href="javascript:view();">안녕하세요. 게시판공지 입니다</a></td>
							<td>1</td>
							<td>관리자</td>
							<td>2025-05-24</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="card-footer text-end">
			<button type="button" class="btn btn-outline-secondary"
				onclick="add();">등록</button>
		</div>
	</div>
	</div>
</body>
</html>