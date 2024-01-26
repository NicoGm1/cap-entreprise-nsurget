<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-5">

    <div class="container">
        <div class="row gameOpener">
            <div class="col-3">
                <div class="container-img">
                    <img alt="${game.name}" src="${game.image}" class="thumbnailShow">
                </div>
            </div>
            <div class="col-9 infocard m-3">
                <div class="text-center m-4">
                    <h1>${game.name} </h1>
                </div>
                <div class="row align-items-center">
                    <div class="col-8">
                        <div class="row">
                            <c:if test="${not empty game.classification}">
                                <h6 class="col-3 mb">classification : </h6>
                                <div class="col-9">${game.classification}
                                </div>
                            </c:if>
                            <c:if test="${not empty game.platforms}">
                                <h6 class="col-3 mt-2">Platform : </h6>

                                <div class="col-9 mt-2">
                                    <c:forEach items="${game.platforms}" var="platform">
                                        <div>
                                            <a class="link-if" href="#">
                                                    <img src="${platform.logo}">
                                            </a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-4">
                        <h3 class="display-6 text-center">OK</h3>
                    </div>
                </div>
                <div>
                </div>
            </div>
        </div>
    </div>

    <h2 class="m-5">Description</h2>

    <div class="text-center">
        <c:out value="${game.description}" escapeXml="false"/>

    </div>


    <c:if test="${lastReview.size() > 0}">
        <h2 class="m-5">Commentaires</h2>
        <div class="row">
            <c:forEach items="${lastReview}" var="review">

                <div class="col-3 align-items-center">
                    <h2 class="display-6">${review.rating}/5</h2>
                </div>
                <figure class="col-8 text-end">
                    <blockquote class="blockquote">
                        <p>"${review.description}"</p>
                    </blockquote>
                    <figcaption class="blockquote-footer">
                        <a class="link-if" href="${UrlRoute.URL_USER}/${review.gamer.nickname}}">
                                ${review.gamer.nickname}
                        </a> - <cite title="Source Title">${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}</cite>
                    </figcaption>
                </figure>

            </c:forEach>
        </div>
    </c:if>
    <h2 class="mt-5">Poster un Commentaires :</h2>
    <security:authorize access="!isAuthenticated()">
        <div class="d-flex mt-1 mb-10">
            <a class="nav-link" href="${UrlRoute.URL_REGISTER}">Register</a><span>&thinsp;ou&thinsp;</span><a
                class="nav-link" href="${UrlRoute.URL_LOGIN}">Login</a>
        </div>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <p1>**FORM HERE**</p1>
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
    </security:authorize>
</div>

<%@ include file="../footer.jsp" %>