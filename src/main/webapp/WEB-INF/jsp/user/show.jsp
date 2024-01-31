<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${user.nickname} - GameReview"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <div class="card mb-3 mx-5">
                <div class="row g-0">

                </div>
            </div>

            <div class="content-padding-2-5-2">
                ok
                <c:if test="${empty userReviews}">
                <h2 class="ms-2">Les reviews de ${user.nickname} :</h2>
                    <c:set var="ignoreImport" scope="request" value="true"/>
                    <c:set var="page" scope="request" value="${userReviews}"/>
                    <c:set var="url" scope="request" value="${UrlRoute.URL_USER}/${user.slug}"/>
                    <%@ include file="../component/pagableReview.jsp" %>
                    <%@ include file="../component/pagination.jsp" %>
                </c:if>
            </div>

        </div>
    </div>
</div>


<%@ include file="../footer.jsp" %>
