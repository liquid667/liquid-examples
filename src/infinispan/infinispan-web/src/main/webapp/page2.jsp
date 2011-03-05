<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<html>
    <head>
        <title>Hello World</title>
    </head>
    <body>
        <f:view>
            <h:form id="mainForm">
                <h:commandLink action="back">
                    <h:outputText value="Home"/>
                </h:commandLink><br/>
                <br/>
                
                <h:dataTable value="#{helloWorld.names}" var="item" border="1">
                	<h:column>
                		<f:facet name="header">
                			<h:outputText value="Items" />
                		</f:facet>
                		<h:outputText value="#{item}" />
                	</h:column>
                	<t:column>
                		<f:facet name="header"><t:outputLabel value="delete" /></f:facet>
                		<t:commandLink action="#{helloWorld.delete}" value="Delete">
                			<f:param name="key" value="#{item}" />
                		</t:commandLink>
                	</t:column>
                </h:dataTable>
                <br/>
                
                <h:outputText value="Cache.size: #{helloWorld.size}" />
                
                
            </h:form>
        </f:view>
    </body>
</html>
