<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Liste des avis"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<div class="container">
<h1 class="title-with-margin">Mes Review</h1>
<div class="bg-dark-rounded-body">

<div class="content-padding-2-5-2">
    <h2>Tes dernières reviews en ligne : ✅</h2>
<c:if test="${not empty page_valid_review.content}">
<c:set var="page" scope="request" value="${page_valid_review}"/>
    <c:set var="url" scope="request" value="${UrlRoute.URL_REVIEW_OWN_LIST}"/>
    <%@ include file="../component/pagableReview.jsp" %>
    <%@ include file="../component/pagination.jsp" %>
</c:if>
    <c:if test="${empty page_valid_review.content}">
        <div class="text-center mt-5">
            <p class="h3">Tu n'as pas encore de review !</p>
            <p class="lead">Partage ton opinion sur ton jeu préféré et aide la communauté à découvrir de nouvelles expériences.</p>

            <a class="btn btn-primary btn-lg" href="${UrlRoute.URL_REVIEW_POST}" role="button">
                Ajouter un commentaire <i class="bi bi-plus-circle-fill"></i>
            </a>
            <p class="mt-3 text-muted">C'est rapide, simple et contribue à enrichir notre communauté de gamers!</p>
            <img src="https://static.hitek.fr/img/actualite/pi4eathbsutml0hd4olq.jpg" class="rounded img-review-what">
        </div>
    </c:if>
<c:if test="${not empty waiting_review}">
    <h2 class="pt-5 mt-5"><a class="btn-link" href="${UrlRoute.URL_REVIEW_OWN_WAITING_LIST}">Tes dernières reviews en cours de moderation : ⌚</a></h2>
    <div class="row">
    <c:forEach var="i" begin="1" end="3" step="1">
        <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
            <div class="main-review-card w-100">
                <p class="text-center">
                    Le ${dateUtils.getDateFormat(waiting_review.get(i).createdAt, "dd/MM/yyyy")}
                    par <a class="btn-link" href="#">${waiting_review.get(i).gamer.nickname}</a> <br>
                <figcaption class="blockquote-footer text-center">
                    <cite title="Source Title">En attente de moderation ⌛</cite>
                </figcaption>
                </p>
                <div class="review-card w-100 d-flex flex-column">
                    <p class="review-description">
                            ${jspUtils.excerpt(waiting_review.get(i).description, 209)}

                    </p>
                    <div class="d-flex justify-content-between mt-auto">
                        <p class="${jspUtils.getCssClas(waiting_review.get(i).rating)}">
                                ${waiting_review.get(i).rating} / 20
                        </p>
                        <a class="btn-link" href="${UrlRoute.URL_GAME}/${waiting_review.get(i).game.slug}">
                                ${waiting_review.get(i).game.name}
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </c:forEach>
    <div class="text-end mt-5">
    <a class="btn-link" href="${UrlRoute.URL_REVIEW_OWN_WAITING_LIST}">voir tes ${waiting_review.size()}
    review en attente de validation</a>
    </div>
    </div>
</c:if>


    </div>
    </div>
    </div>

    <%@ include file="../footer.jsp" %>
