<%@page contentType="text/plain"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="javax.management.*"%>
<%@page import=" org.jboss.mx.util.*"%>
<%
if(request.getParameter("callback") != null) {
   out.print(request.getParameter("callback") + "(");
}
%>
{"components": [
<%
    MBeanServerConnection mbeanServer = MBeanServerLocator.locate();  
    Set ois = mbeanServer.queryMBeans(new ObjectName("WCar:type=version,*"),null);
    String sep="";
    for(Iterator i = ois.iterator() ; i.hasNext() ; ){
        ObjectInstance oi = (ObjectInstance)i.next();
		String component = (String)mbeanServer.getAttribute(oi.getObjectName(),"ComponentName");
		String version = (String)mbeanServer.getAttribute(oi.getObjectName(),"Version");
%><%=sep%>{ "name": "<%= component%>", "version": "<%= version %>" }<% sep=","; }%>]}
<%
if(request.getParameter("callback") != null) {
   out.print(")");
}
%>
