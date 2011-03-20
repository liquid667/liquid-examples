<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<html xmlns:ui="http://java.sun.com/jsf/facelets">
    <head>
        <title>list users</title>

		<jsp:include page="head.jsp" />

    </head>
    <body>
    <jsp:include page="layout.jsp" />

	<div id="center_column">
        <f:view>
            <h:form id="mainForm">
                <h:dataTable value="#{helloWorld.users}" var="item" id="table_block">
                	<h:column>
                		<f:facet name="header">
                			<h:outputText value="Username" />
                		</f:facet>
                		<h:outputText value="#{item.userName}" />
                	</h:column>
                	<h:column>
                		<f:facet name="header">
                			<h:outputText value="Firstname" />
                		</f:facet>
                		<h:outputText value="#{item.firstName}" />
                	</h:column>
                	<h:column>
                		<f:facet name="header">
                			<h:outputText value="Lastname" />
                		</f:facet>
                		<h:outputText value="#{item.lastName}" />
                	</h:column>
                	<t:column>
                		<f:facet name="header"><t:outputLabel value="Edit" /></f:facet>
                		<t:commandLink action="#{helloWorld.edit}" value="Edit">
                			<f:param name="userId" value="#{item.userId}" />
                		</t:commandLink>
                	</t:column>
                	<t:column>
                		<f:facet name="header"><t:outputLabel value="delete" /></f:facet>
                		<t:commandLink action="#{helloWorld.delete}" value="Delete">
                			<f:param name="userId" value="#{item.userId}" />
                		</t:commandLink>
                	</t:column>
                </h:dataTable>
                <br/>

            </h:form>
        </f:view>
	</div>
    </body>
</html>
