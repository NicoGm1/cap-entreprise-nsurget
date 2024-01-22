<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Sitemap"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container">
    <h1 class="mt-5">Sitemap</h1>

    <ul>
        <c:forEach var="link" items="${links}">
            <li><a class="nav-link" href="<c:out value="${link}"/>"><c:out value="${link}"/></a></li>
        </c:forEach>
    </ul>

    <div class="mt-3">
        <a href="${contextPath}/" class="btn btn-primary">Back to Home</a>
    </div>
</div>

<%@ include file="../footer.jsp" %>