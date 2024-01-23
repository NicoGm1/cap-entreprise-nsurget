<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <security:authorize access="!isAuthenticated()">
    <h1 class="text-center mt-5 mb-1 main-title">User Register</h1>
    <div class="justify-content-md-center">
      <f:form modelAttribute="userPostDTO" method="post" action="${UrlRoute.URL_REGISTER}"
              class="form-signin" cssClass="p-5 col-lg-6 col-md-8 col-sm-12 mx-auto">
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="name" class="form-control" placeholder="Nickname"
                     autofocus="true" />
            <f:errors path="name" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="email" class="form-control" placeholder="Email"
                     autofocus="true" />
            <f:errors path="email" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="password" path="password" class="form-control" placeholder="Mot de passe" />
            <f:errors path="password" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="Date" path="birthAt" class="form-control" placeholder="Date de naissance" max="2020-01-01" />
            <f:errors path="birthAt" cssClass="invalid-feedback" />
          </div>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Soumettre</button>
      </f:form>
    </div>
  </security:authorize>

  <security:authorize access="isAuthenticated()">
    <div class="bg-dark-rounded-body">
    <h1 class="title-with-margin">Utilisateur déjà connecté</h1>
    <h2 class="text-center"><form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
      <button class="btn btn-link mt-3" type="submit" tabindex="3">Logout</button>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form></h2>
      <div class="bottom-body"></div>
    </div>

  </security:authorize>


</div>

<%@ include file="../footer.jsp" %>