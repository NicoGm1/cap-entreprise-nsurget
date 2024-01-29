<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="Home - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container main">
    <h1 class="title-with-margin">Bienvenue sur GameReview !</h1>
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <c:if test="${not empty reviews}">
            <h2 class="mx-3 mb-3">Toutes les reviews : 📰</h2>
                <c:set var="page" scope="request" value="${reviews}"/>
                <c:set var="url" scope="request" value="${UrlRoute.URL_HOME}"/>
                <%@ include file="component/pagableReview.jsp" %>
                <%@ include file="component/pagination.jsp" %>
        </div>
        </c:if>
    </div>
</div>

<%@ include file="footer.jsp" %>
