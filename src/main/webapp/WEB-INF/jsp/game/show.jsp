<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${game.name} - GameReview"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <div class="bg-dark-rounded-body">
        <div class="content-padding-2-5-2">
            <div class="card mb-3 mx-5">
                <div class="row g-0">
                    <div class="col-auto">
                        <c:if test="${not empty game.image}">
                        <img src="${game.image}" class="img-fluid rounded-start thumbnailGameShow" alt="${game.name}">
                        </c:if>
                    </div>
                    <div class="col bg-dark rounded-end">
                        <div class="card-body">
                            <div class="text-center">
                                <h1 class="card-title mb-3">${game.name}</h1>
                                <p class="card-text"><small class="text-body-secondary">${game.description}</small></p>
                            </div>

                            <div class="row d-flex mt-5">
                                <div class="col-6 text-end pe-4 border-end">
                                    <p class="card-text">Disponible sur :</p>
                                    <p class="logoContainer">
                                        <c:forEach items="${game.platforms}" var="platform">
                                            <img src="${platform.logo}" alt="${platform.name}" title="${platform.name}" class="logoPlatform">
                                        </c:forEach>
                                    </p>
                                    <p class="card-text">Modèle Économique : ${game.businessModel.name}</p>
                                </div>
                                <div class="col-6 ps-4">
                                    <p class="card-text">Editeur : ${game.publisher.name}</p>
                                    <p class="card-text">Date de sortie : ${game.publishedAt}</p>
                                    <p class="card-text">Classification : ${game.classification.name}</p>
                                    <p class="card-text">Genre : ${game.genre.name}</p>

                                </div>

                            </div>

                        </div>

                    </div>
                    <security:authorize access="hasRole('ROLE_MODERATOR')">
                        <div class="Moderator-option row">
                            <span class="col-2 d-flex align-items-center "><small class="text-body-secondary">Option de moderation </small></span>
                            <span class="col-8 d-flex align-items-center ps-3">
                                        <a href="${UrlRoute.URL_GAME}/${game.slug}">
                                            <button type="submit" class="btn btn-danger">Supprimer</button>
                                        </a>
                                        <a href="${UrlRoute.URL_GAME_PUT}/${game.slug}">
                                            <button type="submit" class="btn btn-secondary ms-3">Modifier</button>
                                        </a>
                    <a class="ms-1 link-green" href="${UrlRoute.URL_GAME_UPLOAD_IMAGE}/${game.slug}">
                        <i class="fa-solid fa-upload"></i>
                    </a>
                                    </span>
                        </div>
                    </security:authorize>
                </div>

            </div>
            <c:if test="${not empty game.trailerYt}">
                <div class="content-padding-2-5-2 pt-0">
                    <h2 class="title-with-margin">Trailer :</h2>
                    <div class="ratio ratio-16x9">
                        <iframe src="${game.trailerYt}" title="YouTube video" allowfullscreen></iframe>
                    </div>
                </div>
            </c:if>
            <div class="content-padding-2-5-2">
                <h2 class="ms-2">Les Dernieres reviews sur ${game.name} :</h2>
                <security:authorize access="hasRole('ROLE_GAMER')">
                    <button class="btn btn-link"
                            title="Ajouter un commentaire"
                            data-hide-show-button="formReview"
                    >
                        Poster un commentaire sur ${game.name}
                    </button>
                    <div class="my-3 d-none"
                         data-hide-show-container="formReview"
                    >
                        <f:form cssClass="col-md-8 col-sm-12 mx-auto"
                                action="${currentUrl}"
                                method="post"
                                modelAttribute="reviewDto"
                        >
                            <div class="mb-3 row">
                                <f:label path="description" class="col-sm-2 col-form-label text-end">Description</f:label>
                                <div class="col-sm-10">
                                    <f:textarea cssClass="form-control" path="description"/>
                                    <f:errors path="description" cssClass="invalid-feedback"/>
                                </div>
                            </div>
                            <div class="my-3 row">
                                <f:label path="rating" class="col-sm-2 col-form-label text-end">Note</f:label>
                                <div class="col-sm-10">
                                    <div class="hstack">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <f:input type="number" min="0" max="20" path="rating" class="form-control pe-3" placeholder="Note" />
                                            <f:errors path="rating" cssClass="invalid-feedback" />
                                        </div>
                                        <div class="ms-3">/20</div>
                                    </div>
                                </div>
                            </div>
                            <div class="my-3 row">
                                <div class="col-sm-2 col-form-label text-end"></div>
                            <div class="col-sm-10">
                            <f:button type="submit" class="btn btn-success">
                                <i class="fa fa-check"></i> Ajouter
                            </f:button>
                            </div>
                            </div>
                        </f:form>
                    </div>
                </security:authorize>
                <c:if test="${not empty game.reviews}">
                    <c:set var="page" scope="request" value="${page_game_review}"/>
                    <c:set var="hideJeu" scope="request" value="true"/>
                    <c:set var="url" scope="request" value="${UrlRoute.URL_GAME}/${game.slug}"/>
                    <%@ include file="../component/pagableReview.jsp" %>
                    <%@ include file="../component/pagination.jsp" %>
                </c:if>
            </div>
        </div>
    </div>
</div>


<%@ include file="../footer.jsp" %>