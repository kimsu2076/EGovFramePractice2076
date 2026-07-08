<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<title>게시물 상세</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="<c:url value='/js/jquery.min.js'/>"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javaScript" language="javascript" defer="defer">
		function list() {
			location.href = "<c:url value='/mainList.do'/>";
		}
		function edit() {
			location.href = "<c:url value='/mgmt.do'><c:param name='idx' value='${boardVO.idx}'/></c:url>";
		}
	</script>
</head>

<body>
	<div class="container my-4">
		<h1>상세 화면</h1>
		<div class="card mb-4">
			<div class="card-header">
				<strong>${boardVO.title}</strong>
			</div>

			<div class="card-body">
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">게시물아이디</label>
					<div class="col-sm-10 d-flex align-items-center">${boardVO.idx}</div>
				</div>

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10 d-flex align-items-center">${boardVO.title}</div>
				</div>

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">등록자</label>
					<div class="col-sm-10 d-flex align-items-center">${boardVO.writerNm}</div>
				</div>

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">등록일</label>
					<div class="col-sm-10 d-flex align-items-center">${boardVO.indate}</div>
				</div>

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">조회수</label>
					<div class="col-sm-10 d-flex align-items-center">${boardVO.count}</div>
				</div>

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<div class="border rounded p-3 bg-light" style="white-space: pre-wrap;">${boardVO.contents}</div>
					</div>
				</div>
			</div>

			<div class="card-footer text-end">
				<c:if test="${!empty sessionScope.userId }">
					<button type="button" class="btn btn-secondary" onclick="edit();">수정</button>
					<button type="button" class="btn btn-danger">삭제</button>
				</c:if>
				<button type="button" class="btn btn-secondary" onclick="list();">목록</button>
			</div>
		</div>
	</div>
</body>

</html>