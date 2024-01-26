<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="ReviewList - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<div class="container">
    <h1 class="title-with-margin">Mes Review</h1>
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <h2>Tes ${page_waiting_review.totalElements} reviews en cours de moderation : ⌚</h2>
            <div class="d-flex">
                <!-- Label à afficher -->
                <c:set var="label" scope="request" value="Date"/>
                <!-- Sur quelle propriété de l'objet on souhaite trier -->
                <c:set var="sortable" value="createdAt"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Note"/>
                <c:set var="sortable" value="rating"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Jeu"/>
                <c:set var="sortable" value="game.name"/>
                <%@ include file="../component/sortable.jsp" %>

                <span class="mt-auto mb-2">
                        <a href="${currentUrl}" class="btn-link">
                            Reset
                        </a>
                    </span>
            </div>
            <div class="row">
                <c:forEach items="${page_waiting_review.content}" var="review">
                    <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                        <div class="main-review-card w-100">
                            <p class="text-center">
                                Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
                                par <a class="btn-link" href="#">${review.gamer.nickname}</a>
                                <figcaption class="blockquote-footer text-center">
                                <cite title="Source Title">En attente de moderation ⌛</cite>
                                </figcaption>
                            </p>
                            <div class="review-card w-100 d-flex flex-column">
                                <p class="review-description">
                                        ${jspUtils.excerpt(review.description, 209)}
                                </p>
                                <div class="d-flex justify-content-between mt-auto">
                                    <p class="${jspUtils.getCssClas(review.rating)}">
                                            ${review.rating} / 20
                                    </p>
                                    <a class="btn-link" href="#">
                                            ${review.game.name}
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>

            </div>
            <c:set var="page" scope="request" value="${page_waiting_review}"/>
            <c:set var="id" scope="request"/>
            <c:set var="url" scope="request" value="${UrlRoute.URL_REVIEW_OWN_WAITING_LIST}"/>
            <%@ include file="../component/pagination.jsp" %>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
