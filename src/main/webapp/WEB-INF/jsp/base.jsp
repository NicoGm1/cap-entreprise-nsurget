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
    <script type="text/javascript" src="${contextPath}/js/component/sortPage.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@floating-ui/core@1.6.0"></script>
    <script src="https://cdn.jsdelivr.net/npm/@floating-ui/dom@1.6.0"></script>
    <script type="text/javascript" src="../js/lib/bootstrap/bootstrap.js"></script>

</head>
<body class="body">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-navbar">
    <div class="row w-100">
        <div class="col-4 d-flex align-items-center">
            <a href="${UrlRoute.URL_HOME}"><img class="logo-navbar m-3 pl-3"
                                                src="https://i.ibb.co/cJvvmmp/Title-Game.png"
                                                alt="Logo Home"
                                                title="Logo Home"/></a>
        </div>
        <div class="col-4 d-flex align-items-center ">
            <div class="d-flex search-bar">
                <input type="text" class="form-control" placeholder="Jeux, Editeur, Platforme ..."
                       data-search-bar>
                <a class="my-auto m-2">
                    <i class="fa fa-magnifying-glass"></i>
                </a>
            </div>
            <div class="search-response-container">
            </div>

        </div>
        <div class="col-4 d-flex align-items-center row">
            <div class="col-4 text-center">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown button
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-8 row text-end">
                <security:authorize access="!isAuthenticated()">
                    <a class="nav-link" href="${UrlRoute.URL_REGISTER}">Inscription</a>
                    <a class="nav-link" href="${UrlRoute.URL_LOGIN}">Connexion</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <div class="d-flex align-items-center">
                        <div>
                            Mon compte
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
<div class="navbar-color-line bg-red">
</div>