<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="ReviewList - GameReview"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container">
    <h1 class="title-with-margin">Review</h1>
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">

            <div class="row">
                <div class="col-8">
                    <h2 class="mb-3">Review de <a class="btn-link" href="#">${review.gamer.nickname}</a> sur <a class="btn-link"
                                                                                                                href="#">${review.game.name}</a>
                    </h2>
                    <p>
                        Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
                    </p>
                    <figcaption class="blockquote-footer">
                        <c:if test="${not empty review.moderator}">
                            Modéré par <cite title="Source Title">${review.moderator.nickname}</cite> -
                            le ${dateUtils.getDateFormat(review.moderatedAt, "dd/MM/yyyy")}
                        </c:if>
                        <c:if test="${empty review.moderator}">
                            <cite title="Source Title">En attente de moderation ⌛</cite>
                        </c:if>
                    </figcaption>
                    <p class="reviewDescription">${jspUtils.excerpt(review.description, 209)}</p>
                </div>
                <div class="col-4 hstack gap-1">
                    <p class="display-6 rounded bg-dark p-2 ms-auto">
                        <span class="${jspUtils.getCssClas(review.rating)}">${review.rating}</span>
                        <span> / 20 </span>
                    </p>
                </div>
            </div>



            <div class="review-card w-100 d-flex flex-column my-5">
                    <div class="card mb-3 mx-5">
                        <div class="row g-0">
                            <div class="col-auto">
                                <img src="${review.game.image}" class="img-fluid rounded-start thumbnailGameShow" alt="${review.game.name}">
                            </div>
                            <div class="col bg-dark rounded-end">
                                <div class="card-body">
                                    <div class="text-center">
                                        <h1 class="card-title mb-3">${review.game.name}</h1>
                                        <p class="card-text"><small class="text-body-secondary">${review.game.description}</small></p>
                                    </div>

                                    <div class="row mt-5">
                                        <div class="col-6">
                                            <p class="card-text">Disponible sur :</p>
                                            <p class="logoContainer">
                                                <c:forEach items="${review.game.platforms}" var="platform">
                                                    <img src="${platform.logo}" class="logoPlatform">
                                                </c:forEach>
                                            </p>
                                            <p class="card-text">Modèle Économique : ${review.game.businessModel.name}</p>
                                        </div>
                                        <div class="col-6 text-end">
                                            <p class="card-text">Editeur : ${review.game.publisher.name}</p>
                                            <p class="card-text">Date de sortie : ${review.game.publishedAt}</p>
                                            <p class="card-text">Classification : ${review.game.classification.name}</p>
                                            <p class="card-text">Genre : ${review.game.genre.name}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
