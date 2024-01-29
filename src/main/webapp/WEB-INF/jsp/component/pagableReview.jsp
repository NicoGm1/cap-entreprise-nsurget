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

                    <c:set var="label" scope="request" value="Jeu"/>
                    <c:set var="sortable" value="game.name"/>
                    <%@ include file="../component/sortable.jsp" %>

                    <c:set var="label" scope="request" value="Gamer"/>
                    <c:set var="sortable" value="gamer.nickname"/>
                    <%@ include file="../component/sortable.jsp" %>

                    <span class="mt-auto mb-2">
                        <a href="${currentUrl}" class="btn-link" title="Réinitialiser les filtres">
    <i class="fa fa-filter-circle-xmark"></i>
</a>
                    </span>
                </div>
            </div>


            <div class="row">
                <c:forEach items="${page.content}" var="review">

                        <div class="col-lg-4 col-md-6 col-sm-12 mt-4">

                            <div class="main-review-card w-100">
                                <p class="text-center">
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
                                <security:authorize access="hasRole('ROLE_MODERATOR')">

                                    <a href="${UrlRoute.URL_REVIEW_DELETE}/${review.id}">
                                        <button type="submit" class="btn btn-danger">Supprimer</button>
                                    </a>
                                    <c:if test="${not empty review.moderator}">
                                    <a href="${UrlRoute.URL_REVIEW_ACCEPT}/${review.id}">
                                        <button type="submit" class="btn btn-secondary">Accepter</button>
                                    </a>
                                    </c:if>

                                </security:authorize>




                                <div class="review-card w-100 d-flex flex-column">
                                    <a href="${UrlRoute.URL_REVIEW}/${review.slug}" class="nav-link">
                                    <p class="review-description">
                                            ${jspUtils.excerpt(review.description, 209)}
                                    </p>
                                    </a>
                                    <div class="d-flex justify-content-between mt-auto">
                                        <p class="${jspUtils.getCssClas(review.rating)}">
                                                ${review.rating} / 20
                                        </p>
                                        <a class="btn-link" href="${UrlRoute.URL_GAME}/${review.game.slug}">
                                                ${review.game.name}
                                        </a>
                                    </div>
                                </div>


                            </div>

                        </div>
                </c:forEach>

            </div>