<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
  <div class="bg-dark-rounded-body">
    <div class="content-padding-2-5-2">
    <h1 class="text-center main-title">Poster une review</h1>
    <div class="justify-content-md-center">

      <f:form modelAttribute="reviewDTO" method="post" action="${UrlRoute.URL_REVIEW_POST}"
              class="form" cssClass="p-5 col-lg-6 col-md-8 col-sm-12 mx-auto">
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
          <input class="form-control" type="text" placeholder="${userLogged.nickname}" aria-label="Disabled input example" disabled>
          </div>
        </div>
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <!-- Utilisez 'itemValue' et 'itemLabel' pour spécifier le champ 'name' -->
            <f:select path="gameName" class="form-select">
              <f:option value="slug"> -- SELECTION DU JEU -- </f:option>
              <f:options items="${games}" itemValue="name" itemLabel="name" />
            </f:select>
            <f:errors path="gameName" cssClass="invalid-feedback" />
          </div>
          </div>
        <div class="mb-3 row">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="text" path="description" class="form-control" placeholder="Ton commentaire..."
                     autofocus="true" rows="3" />
            <f:errors path="description" cssClass="invalid-feedback" />
          </div>
        </div>
        <div class="mb-3 hstack">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <f:input type="number" min="0" max="20" path="rating" class="form-control" placeholder="Note" />
            <f:errors path="rating" cssClass="invalid-feedback" />
          </div>
          <div class="ms-3">/20</div>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Soumettre</button>
      </f:form>
    </div>
    </div>

</div>

<%@ include file="../footer.jsp" %>