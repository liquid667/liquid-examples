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
                <h:outputLabel for="userName" value="Please enter your username" />
                <h:inputText id="userName" value="#{helloWorld.userName}" required="true"/>
                <h:outputLabel for="password" value="Please enter your password" />
                <h:inputText id="password" value="#{helloWorld.password}" required="true"/>

                <h:commandButton value="Login" action="#{helloWorld.login}"/>
                <h:messages showDetail="true" showSummary="false"/>

              </h:panelGrid>

            </h:form>
        </f:view>
    </body>
</html>
