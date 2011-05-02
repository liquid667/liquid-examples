<%@page contentType="text/plain"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="javax.management.*"%>
<%@page import="javax.naming.*"%>
<%
String s = request.getParameter("servers");
StringTokenizer st = new StringTokenizer(s, ",");
while(st.hasMoreElements()) {
        
    MBeanServerConnection mbeanServer = null;
        
    Properties p = new Properties();
    p.put("java.naming.factory.initial",
          "org.jnp.interfaces.NamingContextFactory");
    p.put("java.naming.factory.url.pkgs",
          "org.jboss.naming:org.jnp.interfaces");
    p.put("java.naming.provider.url", st.nextElement());
       
    InitialContext ctx = null;
    try {
        ctx = new InitialContext(p);
        mbeanServer = (MBeanServerConnection) ctx.lookup("jmx/invoker/RMIAdaptor");
%>
<%= mbeanServer.invoke(new ObjectName("wirelesscar:service=agent,type=mq"),"call",new Object[]{}, new String[]{}) %>
<%
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    } finally {
        try {
            ctx.close();
        } catch (NamingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
%>