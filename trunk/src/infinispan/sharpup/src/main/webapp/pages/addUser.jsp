<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<html>
    <head>
        <title>add user</title>
		
		<jsp:include page="head.jsp" />
		
        <script type="text/javascript">
        dojo.require("dijit.MenuBar");
        dojo.require("dijit.MenuBarItem");
        dojo.require("dijit.PopupMenuBarItem");
        dojo.require("dijit.Menu");
        dojo.require("dijit.MenuItem");
        dojo.require("dijit.PopupMenuItem");

        dojo.addOnLoad(function() {
            });
        </script>
    </head>
    <body>
    <jsp:include page="layout.jsp" />

	<div id="center_column">
	<f:view>
		<h:form id="mainForm">
		  <h:panelGrid columns="2">
		    <h:outputLabel for="userName" value="Please enter your username" />
		    <h:inputText id="userName" value="#{helloWorld.userName}" required="true"/>
		    <h:outputLabel for="password" value="Please enter your password" />
		    <h:inputText id="password" value="#{helloWorld.password}" required="true"/>
		    <h:outputLabel for="firstName" value="Please enter your firstname" />
		    <h:inputText id="firstName" value="#{helloWorld.firstName}" required="true"/>
		    <h:outputLabel for="lastName" value="Please enter your lastname" />
		    <h:inputText id="lastName" value="#{helloWorld.lastName}" required="true"/>
		
		    <h:commandButton value="Save" action="#{helloWorld.update}"/>
		    <h:messages showDetail="true" showSummary="false"/>
		
		  </h:panelGrid>
		</h:form>
	</f:view>
	</div>
    </body>
</html>
