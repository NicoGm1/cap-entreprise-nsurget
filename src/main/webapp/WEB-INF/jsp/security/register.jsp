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
            <f:input type="text" path="email" class="form-control" placeholder="Email"
                     autofocus="true" />
            <f:errors path="email" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="password" path="password" class="form-control" placeholder="Password" />
            <f:errors path="password" cssClass="invalid-feedback" />
          </div>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
      </f:form>
    </div>
  </security:authorize>

  <security:authorize access="isAuthenticated()">
    <h1 class="text-center mt-5 mb-1">User Already Login</h1>
    <h2 class="text-center mt-5 mb-1">Log out =)</h2>
  </security:authorize>

</div>

<%@ include file="../footer.jsp" %>