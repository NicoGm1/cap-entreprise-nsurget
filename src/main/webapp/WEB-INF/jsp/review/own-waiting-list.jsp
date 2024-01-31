<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Liste en attente - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<div class="container">
    <h1 class="title-with-margin">Mes Review</h1>
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <h2>Tes ${page_waiting_review.totalElements} reviews en cours de moderation : ⌚</h2>

            <c:set var="page" scope="request" value="${page_waiting_review}"/>
            <c:set var="url" scope="request" value="${UrlRoute.URL_REVIEW_OWN_WAITING_LIST}"/>
            <%@ include file="../component/pagableReview.jsp" %>
            <%@ include file="../component/pagination.jsp" %>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
