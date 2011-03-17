<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<html>
    <head>
        <title>cache</title>
        <style type="text/css" media="css/base.css"></style>        
		<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js" type="text/javascript"></script>
		<script type="text/javascript">
		dojo.require("dojo.fx");

		function init(){
            dojo.byId("greeting").innerHTML += ", from " + dojo.version;

            dojo.fx.slideTo({
                top: 100,
                left: 200,
                node: dojo.byId("greeting")
                }).play();
		}
		
        dojo.ready(init);
		</script>
    </head>
    <body>
    <h1 id="greeting">Hello</h1>
        <f:view>
            <h:form id="mainForm">
            
                <h:commandLink action="addUser">
                    <h:outputText value="Add user"/>
                </h:commandLink>
                <h:commandLink action="listUsers.jsp">
                	<h:outputText value="List Users" />                
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
