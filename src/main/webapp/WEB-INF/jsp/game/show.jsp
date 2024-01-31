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


<%--        <div class="row gameOpener">--%>
<%--            <div class="col-3">--%>
<%--                <div class="container-img">--%>
<%--                    <img alt="${game.name}" src="${game.image}" class="thumbnailShow">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-9 infocard m-3">--%>
<%--                <div class="text-center m-4">--%>
<%--                    <h1>${game.name} </h1>--%>
<%--                </div>--%>
<%--                <div class="row align-items-center">--%>
<%--                    <div class="col-8">--%>
<%--                        <div class="row">--%>
<%--                            <c:if test="${not empty game.classification}">--%>
<%--                                <h6 class="col-3 mb">classification : </h6>--%>
<%--                                <div class="col-9">${game.classification}--%>
<%--                                </div>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${not empty game.platforms}">--%>
<%--                                <h6 class="col-3 mt-2">Platform : </h6>--%>

<%--                                <div class="col-9 mt-2">--%>
<%--                                    <c:forEach items="${game.platforms}" var="platform">--%>
<%--                                        <div>--%>
<%--                                            <a class="link-if" href="#">--%>
<%--                                                    <img src="${platform.logo}">--%>
<%--                                            </a>--%>
<%--                                        </div>--%>
<%--                                    </c:forEach>--%>
<%--                                </div>--%>
<%--                            </c:if>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-4">--%>
<%--                        <h3 class="display-6 text-center">OK</h3>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <h2 class="m-5">Description</h2>--%>

<%--    <div class="text-center">--%>
<%--        <c:out value="${game.description}" escapeXml="false"/>--%>

<%--    </div>--%>


<%--    <c:if test="${lastReview.size() > 0}">--%>
<%--        <h2 class="m-5">Commentaires</h2>--%>
<%--        <div class="row">--%>
<%--            <c:forEach items="${lastReview}" var="review">--%>

<%--                <div class="col-3 align-items-center">--%>
<%--                    <h2 class="display-6">${review.rating}/5</h2>--%>
<%--                </div>--%>
<%--                <figure class="col-8 text-end">--%>
<%--                    <blockquote class="blockquote">--%>
<%--                        <p>"${review.description}"</p>--%>
<%--                    </blockquote>--%>
<%--                    <figcaption class="blockquote-footer">--%>
<%--                        <a class="link-if" href="${UrlRoute.URL_USER}/${review.gamer.nickname}}">--%>
<%--                                ${review.gamer.nickname}--%>
<%--                        </a> - <cite title="Source Title">${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}</cite>--%>
<%--                    </figcaption>--%>
<%--                </figure>--%>

<%--            </c:forEach>--%>
<%--        </div>--%>
<%--    </c:if>--%>
<%--    <h2 class="mt-5">Poster un Commentaires :</h2>--%>
<%--    <security:authorize access="!isAuthenticated()">--%>
<%--        <div class="d-flex mt-1 mb-10">--%>
<%--            <a class="nav-link" href="${UrlRoute.URL_REGISTER}">Register</a><span>&thinsp;ou&thinsp;</span><a--%>
<%--                class="nav-link" href="${UrlRoute.URL_LOGIN}">Login</a>--%>
<%--        </div>--%>
<%--    </security:authorize>--%>
<%--    <security:authorize access="isAuthenticated()">--%>
<%--        <p1>**FORM HERE**</p1>--%>
<%--        <f:form modelAttribute="reviewDto" method="post"--%>
<%--                action="${s:mvcUrl('AppReview#create').arg(0, game.slug).build()}"--%>
<%--                cssClass="p-5 col-lg-6 col-md-8 col-sm-12 mx-auto">--%>

<%--            <div class="mb-3 row">--%>
<%--                <f:label path="title" class="col-sm-2 col-form-label text-end">Title :</f:label>--%>
<%--                <div class="col-sm-10">--%>
<%--                    <f:input type="text" class="form-control" path="title" placeholder="Titre de ma review"/>--%>
<%--                    <f:errors path="title" class="invalid-feedback"/>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="mb-3 row">--%>
<%--                <f:label path="content" class="col-sm-2 col-form-label text-end">Content :</f:label>--%>
<%--                <div class="col-sm-10">--%>
<%--                    <f:textarea class="form-control" path="content" rows="2" placeholder="Ce jeu est super !"/>--%>
<%--                    <f:errors path="content" class="invalid-feedback"/>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="mb-3 row">--%>
<%--                <f:label path="rating" class="col-sm-2 col-form-label text-end">Rating :</f:label>--%>
<%--                <div class="col-5">--%>
<%--                    <div class="input-group">--%>
<%--                        <f:input type="number" class="form-control" path="rating" placeholder="4.5" step="0.1"--%>
<%--                                 value="4.5" max="5"/>--%>
<%--                        <div class="input-group-append col-2">--%>
<%--                            <div class="m-1">/5</div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <f:errors path="rating" class="invalid-feedback"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <f:input type="number" path="gamer" hidden="hidden"/>--%>
<%--            <f:input type="number" path="game" hidden="hidden"/>--%>
<%--            <f:button class="btn btn-secondary" type="reset">Reset</f:button>--%>
<%--            <f:button class="btn btn-primary" type="submit">Submit</f:button>--%>
<%--        </f:form>--%>
<%--    </security:authorize>--%>
<%--</div>--%>

<%@ include file="../footer.jsp" %>