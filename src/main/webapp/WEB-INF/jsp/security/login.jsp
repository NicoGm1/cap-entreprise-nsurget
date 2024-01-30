<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container main">

  <security:authorize access="!isAuthenticated()">

    <h1 class="text-center mt-5 mb-1 main-title">Connection</h1>

    <div class="form-group ${error != null ? 'has-error' : ''}">
      <span>${message}</span>
      <div class=" justify-content-md-center">
        <form method="POST" action="${UrlRoute.URL_LOGIN}"
              class="form-signin p-5 col-lg-6 col-md-8 col-sm-12 mx-auto">
          <div class="form-floating mb-3 user-form">
            <input name="username" type="text" class="form-control" id="floatingInput" placeholder="Pseudo">
            <label for="floatingInput">Pseudo</label>
          </div>
          <div class="form-floating user-form">
            <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
          </div>
          <p class="invalid-feedback">${error}</p>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="row ">
            <div class="col-md-4 mt-2">
              <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
            </div>
            <div class="col-md-8 mt-2 text-md-end">
                <a href="${UrlRoute.URL_REGISTER}" class="btn btn-lg btn-secondary btn-block">
                  Créer un compte !
                </a>
            </div>
          </div>

        </form>
      </div>
    </div>
  </security:authorize>
  <security:authorize access="isAuthenticated()">
    <div class="bg-dark-rounded-body text-center">
      <h1 class="title-with-margin">Utilisateur déjà connecté</h1>
      <div class="content-padding-2-5-2">
        <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
          <button class="btn btn-link" type="submit" tabindex="3">Logout</button>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
      </div>
    </div>

  </security:authorize>
</div>

<%@ include file="../footer.jsp" %>