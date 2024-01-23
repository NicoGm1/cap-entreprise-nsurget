<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "GameReview";
    }
    request.setAttribute("title", title);
%>

<html>
<head>
    <title>${title}</title>
    <link href="${contextPath}/css/main.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <script type="text/javascript" src="${contextPath}/js/page/search-bar.js"></script>

</head>
<body class="body">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-navbar">
    <div class="row w-100">
        <div class="col-5 d-flex align-items-center">
            <a href="${UrlRoute.URL_HOME}"><img class="logo-navbar m-3 pl-3"
                                                src="https://i.ibb.co/0YmQCn4/Game-Review.png"
                                                alt="Logo Home"
                                                title="Logo Home"/></a>
        </div>
        <div class="col-5 d-flex align-items-center">
            <div class="d-flex search-bar">
                <input type="text" class="form-control" placeholder="Jeux, Editeur, Platform ..."
                       data-search-bar>
                <a class="my-auto m-3">
                    <i class="fa fa-magnifying-glass"></i>
                </a>
            </div>
            <div class="search-response-container">
            </div>

        </div>
        <div class="col-2 d-flex align-items-center row">
            <div class="col-4 text-center">
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="nav-link" href="${UrlRoute.URL_ADMIN}">Admin Panel</a>
                </security:authorize>
            </div>
            <div class="col-8 row text-end">
                <security:authorize access="!isAuthenticated()">
                    <a class="nav-link" href="${UrlRoute.URL_REGISTER}"> Register</a>
                    <a class="nav-link" href="${UrlRoute.URL_LOGIN}">Login</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <div class="d-flex align-items-center">
                        <div>
                            Mon compte :
                        </div>
                        <a class="btn btn-link" href="${UrlRoute.URL_USER}/${userLogged.nickname}">
                                    ${userLogged.nickname}
                        </a>
                        <div>
                            -
                        </div>
                        <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                            <button class="btn btn-link mt-3" type="submit" tabindex="3">Logout</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>

                </security:authorize>
            </div>
        </div>
    </div>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">

        </div>
    </div>
</nav>