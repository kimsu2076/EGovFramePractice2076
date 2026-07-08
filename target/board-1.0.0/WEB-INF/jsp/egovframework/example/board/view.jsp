<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<title>Bootstrap 5 Example</title>
			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="/css/bootstrap/css/bootstrap.min.css">
			<script src="/js/jquery.min.js"></script>
			<script src="/css/bootstrap/js/bootstrap.min.js"></script>
			<script type="text/javaScript" language="javascript" defer="defer">
	function list() {
		location.href = "<c:url value='/mainList.do'/>";
	}
</script>
		</head>

		<body>
			<div class="container my-4">
				<h1>상세 화면</h1>
				<div class="card mb-4">
					<div class="card-header">
						<label>생각중:</label>
					</div>

					<div class="card-body">
						<form class="row g-3" method="post" action="/">

							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">게시물아이디:</label>
								<div class="col-sm-10 d-flex align-items-center">
									<div>게시물아이디</div>
								</div>
							</div>

							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">제목:</label>
								<div class="col-sm-10 d-flex align-items-center">
									<div>제목</div>
								</div>
							</div>

							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">등록자/등록일:</label>
								<div class="col-sm-10 d-flex align-items-center">
									<div>등록자/등록일</div>
								</div>
							</div>

							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">내용:</label>
								<div class="col-sm-10 d-flex align-items-center">
									<div>내용</div>
								</div>
							</div>
						</form>
					</div>

					<div class="card-footer text-end">
						<c:if test="${!empty sessionScope.userId }">
							<button type="button" class="btn btn-secondary">수정</button>
							<button type="button" class="btn btn-secondary">삭제</button>
						</c:if>
						<button type="button" class="btn btn-secondary" onclick="list();">목록</button>
					</div>

					<div class="mt-3 p-2 bg-light border rounded">작성자/작성일</div>

					<div class="mt-3 p-4 bg-body-tertiary border rounded">
						<form method="post" action="/reply.do">
							<div class="row mb-3">
								<label for="writer" class="col-sm-2 col-form-label">작성자/작성일:</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="writer" name="writer"
										placeholder="작성자를 입력하세요" maxlength="15">
								</div>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="indate" name="indate"
										placeholder="작성일을 입력하세요" maxlength="10">
								</div>
							</div>

							<div class="mb-3 row">
								<label for="reply" class="col-sm-2 col-form-label">내용:</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="3" id="reply" name="reply"
										maxlength="300"></textarea>
								</div>
							</div>

							<div class="text-end">
								<button type="submit" class="btn btn-primary">작성</button>
							</div>
						</form>
					</div>
				</div>
		</body>

		</html>