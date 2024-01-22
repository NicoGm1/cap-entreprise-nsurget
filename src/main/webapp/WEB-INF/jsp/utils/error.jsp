<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Error"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>
<div class="container " >
    <h1 class="mt-5">Oops! Something went wrong. 😞</h1>
    <p>We're sorry, but an unexpected error occurred. Please try again later.</p>
    <p>Error : ${code}</p>
    <p>Try all other path : <a href="${WebUrlRoute.URL_SITEMAP}" class="btn-primary">All disponible path !</a></p>

    <div class="mt-5 mb-5">
        <a href="${WebUrlRoute.URL_HOME}" class="btn btn-primary">Back to Home</a>
    </div>
</div>

<%@ include file="../footer.jsp" %>