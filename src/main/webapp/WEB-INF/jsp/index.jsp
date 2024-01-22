<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container main">
    <h1 class="m-5 main-title">Bienvenue sur GameReview !</h1>
    <div class="bg-dark p-3 rounded">
        <h2 class="my-5">Les dernières review :</h2>
    </div>
</div>

<%@ include file="footer.jsp" %>
