<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="ReviewList - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<div class="container">
    <h1 class="title-with-margin">Moderation des Reviews</h1>
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <h2>Les ${waiting_review.totalElements} reviews en cours de moderation : ⌚</h2>

            <c:set var="page" scope="request" value="${waiting_review}"/>
            <c:set var="url" scope="request" value="${UrlRoute.URL_REVIEW_MODERATOR}"/>
            <%@ include file="../component/pagableReview.jsp" %>
            <%@ include file="../component/pagination.jsp" %>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
