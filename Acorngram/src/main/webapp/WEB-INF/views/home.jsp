<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MySite</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/main.css" />
	
</head>
<body>
	<header>
		<div class="container">
			<h1 class="logo">LOGO</h1>
			<c:choose>
				<c:when test="${id eq null }">
					<a href="user/signForm.jsp" role="button">LOGIN</a>
				</c:when>
				
				<c:otherwise>
					<div class="nav-container">
						<nav class="nav">
							<span><i class="material-icons"> menu </i> @${id }</span>
							<ul>
								<li><a href="#">Profile</a></li>
								<li><a href="user/private/settings.jsp">settings</a></li>
								<li><a href="javascript:confirmAccess('signout.jsp')">Sign out</a></li>
							</ul>				
						</nav>
						<a href="javascript:toggleWritePopup()" role="button"><i class="material-icons"> create </i> Create </a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	<main>
		<div class="container">
			<c:if test="${id eq null }">
				<section class="write-post <c:if test="${list.size() lt 6 }">is-visible</c:if>" >
					<form action="post/private/writePost.jsp" id="write-post" enctype="multipart/form-data" method="post">
						<fieldset>
							<legend>
								<span>Pikaboo?</span>
								<button>Send</button>
							</legend>
							<div class="flexbox">
								<label for="write-post-img">
									<input type="file" name="write-post-img" id="write-post-img" accept="image/*" required/>
									<div class="write-post__area">
										<i class="material-icons"> insert_photo </i>
										<p>Upload Photo</p>
									</div>
									<img id="preview">
								</label>
								<textarea name="description" id="description" required></textarea>
							</div>
						</fieldset>
					</form>
				</section>
			</c:if>
			<section class="timeline">
			<c:forEach var = "dto" items="${list }">
				<article class="post post-${dto.postNum }">
					<div class="post-header">
						<img src="" alt="" class="tl__icon"/> <!-- icon -->
						<hgroup>
							<h5> ${dto.name } </h5>
							<h6> @${dto.id } </h6>
						</hgroup>
						<c:if test="${dto.id eq id }">
							<a href="javascript:deletePost(${dto.postNum })" class="material-icons"> delete </a>
						</c:if>
					</div>
					
					<div class="post-img" style="
						background-image: url('${pageContext.request.contextPath}/upload/${dto.imgSrc }')">
					</div>
					<div class="post-info">
						<div class="post-info__like">
							<a href="javascript:likeControl(${dto.postNum })" role="button" class="btn-like material-icons">favorite_border</a>
							<span class="count-like">${dto.likeCount }</span>
						</div>
						<div class="post-info__regdate">
							<time datetime="${dto.regdate};"> </time>
						</div>
					</div>
					<div class="post-body">
						<h3>${dto.name }</h3>
						<p>${dto.description }</p>
					</div>
				</article>
			</c:forEach>
			</section>
		</div>
	</main>
	<footer>
		
	</footer>
	
	<section class="panel-alert">
		
	</section>
	
	<script crossorigin="anonymous" src="https://polyfill.io/v3/polyfill.min.js"></script>
	<!-- jquery used anyway -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/src/js/macy.js"></script>
	<script src="${pageContext.request.contextPath}/src/js/core.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>
	<script>
		moment.locale('ko'),
		document.querySelectorAll('time').forEach(e=>{
			//	절대시간 (2019년 m월 dd일 ...)
			//e.innerText = moment(e.dataTime).format('LLLL');
			
			//	상대시간 (n분전...)
			e.innerText = moment(e.dateTime).fromNow();
		})
	</script>
</body>
</html>
