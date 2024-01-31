<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex justify-content-between">
    <div class="d-flex">
        <!-- Label à afficher -->
        <c:set var="label" scope="request" value="Date"/>
        <!-- Sur quelle propriété de l'objet on souhaite trier -->
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Note"/>
        <c:set var="sortable" value="rating"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:if test="${!hideJeu}">
            <c:set var="label" scope="request" value="Jeu"/>
            <c:set var="sortable" value="game.name"/>
            <%@ include file="../component/sortable.jsp" %>
        </c:if>
        <c:set var="label" scope="request" value="Gamer"/>
        <c:set var="sortable" value="gamer.nickname"/>
        <%@ include file="../component/sortable.jsp" %>

        <span class="mt-auto mb-2">
                        <a href="${currentUrl}" class="btn-link" title="Réinitialiser les filtres">
    <i class="fa fa-filter-circle-xmark"></i>
</a>
                    </span>
        <security:authorize access="hasRole('ROLE_MODERATOR')">
            <c:if test="${addModeratorFilter}">
                    <span class="mt-auto ms-5">
                        <div class="dropdown">
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${UrlRoute.URL_HOME}">Tout les avis</a></li>
                            <li><a class="dropdown-item"
                                   href="${UrlRoute.URL_REVIEW_MODERATOR}">Avis non modéré</a></li>
                        </ul>
                        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                                Filtre moderation
                        </a>
                    </div>
                    </span>
            </c:if>
        </security:authorize>
    </div>
</div>


<div class="row">
    <c:forEach items="${page.content}" var="review">
        <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
            <div class="main-game-card w-100 rounded">
                <div class="card rounded mb-2">
                    <div class="row g-0 bg-dark rounded">
                        <p class="text-center pt-2">
                            Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
                            par <a class="btn-link" href="#">${review.gamer.nickname}</a> <br>
                        <figcaption class="blockquote-footer text-center">
                            <c:if test="${not empty review.moderator}">
                                Modéré par <cite title="Source Title">${review.moderator.nickname}</cite> -
                                le ${dateUtils.getDateFormat(review.moderatedAt, "dd/MM/yyyy")}
                            </c:if>
                            <c:if test="${empty review.moderator}">
                                <cite title="Source Title">En attente de moderation ⌛</cite>
                            </c:if>
                        </figcaption>


                        <div class="review-card w-100 d-flex flex-column px-3">
                            <a href="${UrlRoute.URL_REVIEW}/${review.slug}" class="nav-link">
                                <p class="review-description">
                                <div class="content-description">
                                    <p class="inner-description">
                                        <span>${review.description}</span>
                                    </p>
                                </div>
                                </p>
                            </a>
                            <div class="d-flex justify-content-between mt-auto">
                                <p>
                                    <span class="${jspUtils.getCssClas(review.rating)}">${review.rating}</span>
                                    <span> / 20 </span>
                                </p>
                                <c:if test="${!hideJeu}">
                                <a class="btn-link" href="${UrlRoute.URL_GAME}/${review.game.slug}">
                                        ${review.game.name}
                                </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <security:authorize access="hasRole('ROLE_MODERATOR')">
                        <div class="Moderator-option row">
                            <span class="col-3 d-flex align-items-center mt-auto"><small class="text-body-secondary">Option de moderation </small></span>
                            <span class="col-8 d-flex align-items-center mt-auto ps-3">
                                        <a href="${UrlRoute.URL_REVIEW_DELETE}/${review.id}">
                                            <button type="submit" class="btn btn-danger">Supprimer</button>
                                        </a>
                                        <c:if test="${empty review.moderator}">
                                            <a href="${UrlRoute.URL_REVIEW_ACCEPT}/${review.id}">
                                                <button type="submit" class="btn btn-secondary ms-3">Accepter</button>
                                            </a>
                                        </c:if>
                                    </span>
                        </div>
                    </security:authorize>
                </div>


            </div>

        </div>

    </c:forEach>

</div>