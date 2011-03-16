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
            
                <h:commandLink action="back">
                    <h:outputText value="Home"/>
                </h:commandLink>
                
                <br/><br/>
                
              <h:panelGrid columns="2">
                <h:outputLabel for="userId" value="Please enter your userid" />
                <h:inputText id="userId" value="#{helloWorld.userId}" required="true"/>
                <h:outputLabel for="userName" value="Please enter your username" />
                <h:inputText id="userName" value="#{helloWorld.userName}" required="true"/>
                <h:outputLabel for="password" value="Please enter your password" />
                <h:inputText id="password" value="#{helloWorld.password}" required="true"/>
                <h:outputLabel for="firstName" value="Please enter your firstname" />
                <h:inputText id="firstName" value="#{helloWorld.firstName}" required="true"/>
                <h:outputLabel for="lastName" value="Please enter your lastname" />
                <h:inputText id="lastName" value="#{helloWorld.lastName}" required="true"/>

                <h:commandButton value="Save" action="#{helloWorld.add}"/>
                <h:messages showDetail="true" showSummary="false"/>

              </h:panelGrid>

            </h:form>
        </f:view>
    </body>
</html>
