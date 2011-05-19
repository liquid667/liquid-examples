<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<html xmlns:ui="http://java.sun.com/jsf/facelets">
    <head>
        <title>cci rest client</title>
    </head>
    <body>
        <f:view>
            <h:form id="mainForm">
            	<h:outputLabel for="serviceType" value="Service Type" />
            	<h:selectOneMenu id="serviceType">
            		<f:selectItem id="ecallmanual" itemLabel="Ecall Manual" itemValue="ecallmanual" />
            		<f:selectItem id="ecallautomatic" itemLabel="Ecall Automatic" itemValue="ecallautomatic" />
            		<f:selectItem id="icall" itemLabel="Icall" itemValue="icall" />
            	</h:selectOneMenu>
            	<h:outputLabel for="eventId" value="Event Id" />
            	<h:inputText id="eventId" label="Event Id" />
            	
            	<h:commandButton action="sendRestRequest" />
            </h:form>
        </f:view>
    </body>
</html>
