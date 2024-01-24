<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="Home - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container main">
    <h1 class="title-with-margin mb-5">Bienvenue sur GameReview !</h1>
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
        <h2>Les dernières review :</h2>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
