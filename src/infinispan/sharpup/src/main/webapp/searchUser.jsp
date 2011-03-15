<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<html>
    <head>
        <title>cache</title>
    </head>
    <body>
        <f:view>
            <h:form id="mainForm">
              <h:panelGrid columns="2">
                <h:outputLabel for="queryString" value="Enter your search criteras" />
                <h:inputText id="queryString" value="#{userSearch.queryString}" required="true"/>

                <h:commandButton value="Login" action="#{userSearch.searchUser}"/>
                <h:messages showDetail="true" showSummary="false"/>

              </h:panelGrid>

            </h:form>
        </f:view>
    </body>
</html>
