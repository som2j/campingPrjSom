<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="${context}/resources/assets/css/main.css" />

<meta id="_csrf" name="_csrf" content="${_csrf.token}"></meta>
<meta id="_csrf_header" name="_csrf_header"
	content="${_csrf.headerName}"></meta>

</head>
<body>
	<!-- Wrapper -->
	<div id="wrapper">
		<!-- Main -->
		<div id="main">
			<div class="inner">
				<%@ include file="/WEB-INF/views/include/header.jsp"%>
				<!-- Content -->
				<form id="createRoom">
					<label>myUserId : <input id="userId" text="JaeHyun">
					</label> <label>groupName : <input id="groupName">
					</label> <label>maxMember : <input id="maxMember">
					</label> <label>groupMaster : <input id="groupMaster">
					</label>
					<button>방생성하기</button>
				</form>

				<div class="row gtr-200">
					<div class="col-6 col-12-medium">
					<h3>채팅방</h3>
					
						<c:forEach items="${MyGroup['GroupMember']}" var="Group"
							varStatus="status">

							<div class="box">
								<c:set var="count" value="${status.count}"></c:set>
								<c:forEach items="${MyGroup['campingGroup']}" var="campingGroup"
									varStatus="st">
									<c:if test="${count eq st.count}">
										<p>${st.count}.
											<a
												href="/groupChat/chatRoom?roomId=${Group.roomId}&groupIdx=${Group.groupIdx}">
												${campingGroup.groupName} </a> <p>GroupMaster : ${campingGroup.groupMaster}</p>
										</p>
										
										<p>${campingGroup.currentMember} / ${campingGroup.maxMember}</p>
									</c:if>
								</c:forEach>
								<br>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- Sidebar -->
		<%@ include file="/WEB-INF/views/include/sidebar.jsp"%>
	</div>
	<%@ include file="/WEB-INF/views/include/commonScripts.jsp"%>
	<script src="${context}/resources/assets/js/chat/createRoom.js"></script>
	<script type="text/javascript">
		
	</script>

</body>
</html>
