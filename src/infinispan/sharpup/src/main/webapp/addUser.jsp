<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<html>
    <head>
        <title>cache</title>
        
		<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.10.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
		<script type="text/javascript">
		$(function(){
			// Tabs
			$('#tabs').tabs();
		});
		</script>
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
              
		<!-- Tabs -->
		<h2 class="demoHeaders">Tabs</h2>
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">First</a></li>
				<li><a href="#tabs-2">Second</a></li>
				<li><a href="#tabs-3">Third</a></li>
			</ul>
			<div id="tabs-1">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>
			<div id="tabs-2">Phasellus mattis tincidunt nibh. Cras orci urna, blandit id, pretium vel, aliquet ornare, felis. Maecenas scelerisque sem non nisl. Fusce sed lorem in enim dictum bibendum.</div>
			<div id="tabs-3">Nam dui erat, auctor a, dignissim quis, sollicitudin eu, felis. Pellentesque nisi urna, interdum eget, sagittis et, consequat vestibulum, lacus. Mauris porttitor ullamcorper augue.</div>
		</div>

            </h:form>
        </f:view>
    </body>
</html>
