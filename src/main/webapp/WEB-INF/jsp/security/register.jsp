<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>
<div class="position-relative"></div>
<div class="black-bg position-absolute"></div>
<div class="container">
  <security:authorize access="!isAuthenticated()">
    <h1 class="text-center mt-5 mb-1 main-title">User Register</h1>
    <div class="justify-content-md-center">
      <f:form modelAttribute="userPostDTO" method="post" action="${UrlRoute.URL_REGISTER}"
              class="form-signin" cssClass="pt-5 col-lg-6 col-md-8 col-sm-12 mx-auto">
        <div class="mb-3 row">
          <div class="col-lg-3 col-md-3 col-sm-12 text-end">Pseudo*
          </div>
          <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="nickname" class="form-control" placeholder="Pseudo"
                     autofocus="true" />
            <f:errors path="nickname" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 row">
          <div class="col-lg-3 col-md-3 col-sm-12 text-end">E-mail*
          </div>
          <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="email" class="form-control" placeholder="Email"
                     autofocus="true" />
            <f:errors path="email" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 row">
          <div class="col-lg-3 col-md-3 col-sm-12 text-end">Mot de passe*
          </div>
          <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
            <f:input type="password" path="password" class="form-control" placeholder="Mot de passe" />
            <f:errors path="password" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 row">
          <div class="col-lg-3 col-md-3 col-sm-12 text-end">Date de naissance*
          </div>
          <div class="col-lg-8 col-md-8 col-sm-12 form-group ${status.error ? 'has-error' : ''}">
            <f:input type="Date" path="birthAt" class="form-control" placeholder="Date de naissance" max="2009-01-01" />
            <f:errors path="birthAt" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mt-3 row">
          <div class="col-lg-3 col-md-2 col-sm-12 text-end">

          </div>
          <div class="col-lg-8 col-md-8 col-sm-12">
            <button class="btn btn-lg btn-primary btn-block"
                    type="submit">Soumettre
            </button>
          </div>
        </div>
      </f:form>
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