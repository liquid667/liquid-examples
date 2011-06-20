/*
 * Created on Jun 17, 2004
 * 
 * Copyright (c) 2003 WirelessCar. All Rights Reserved.
 * 
 * This SOURCE CODE FILE, which has been provided by WirelessCar as part of a
 * WirelessCar product for use ONLY by licensed users of the product, includes
 * CONFIDENTIAL and PROPRIETARY information of WirelessCar.
 * 
 * Last change made by: $Author: mikael $ at $Date: 2004/06/18 08:38:55 $
 * 
 * @version $Revision: 1.2 $
 */
package com.wirelesscar.common.property.mbean;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.jboss.util.Strings;
import org.jboss.util.property.Property;
import org.jboss.util.property.PropertyGroup;
import org.jboss.util.property.PropertyListener;

/**
 * A service to load properties from property-files an place 
 * them in the system properties.
 * The property-files are monitored for changes, so when a file 
 * has been changed, these properties are reloaded.
 * 
 * This code is a snapshot from org.jboss.varia.property.SystemPropertiesService, Revision: 1.11
 * extended with the timed update functionality.
 *
 * @jmx:mbean name="com.wirelesscar.common.property:type=Service,name=MonitoredSystemProperty"
 *            extends="org.jboss.system.ServiceMBean"
 *
 * @author Mikael Thorman
 * 
 * @version $Revision: 1.2 $
 */
public class SystemPropertiesServiceMonitoring implements SystemPropertiesServiceMonitoringMBean
{

    /** The server's home dir as a string (for making urls). */
    protected String serverHome;

    /** A list of timer tasks */
    private ArrayList notifiers = new ArrayList();

    /** The timer used to handle the timer tasks */
    private Timer timer = new Timer(true);

    /** The timespan between polling the files for changes (milliseconds) */
    private long monitorInterval = 60000;

    ///////////////////////////////////////////////////////////////////////////
    //                    Property/PropertyManager Access //
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Set a system property.
     *
     * @jmx:managed-operation
     *
     * @param name    The name of the property to set.
     * @param value   The value of the property.
     * @return        A string informing about the change & what the previous value was.
     */
    public String setProperty(final String name, final String value)
    {
        String reply;
        String prevValue = Property.set(name, value);
        reply = name + ": " + prevValue + " -> " + value;

        log.info("A property was set. " + reply);

        return reply;
    }

    /**
     * Get a system property.
     * 
     * @jmx:managed-operation
     *
     * @param name          Property name
     * @param defaultValue  Default property value
     * @return              Property value or default
     */
    public String getProperty(final String name, final String defaultValue)
    {
        return Property.get(name, defaultValue);
    }

    /**
     * Get a system property.
     *
     * @jmx:managed-operation
     *
     * @param name       Property name
     * @return           Property value or null
     */
    public String getProperty(final String name)
    {
        return Property.get(name);
    }

    /**
     * Remove a system property.
     *
     * @jmx:managed-operation
     *
     * @param name    The name of the property to remove.
     * @return        Removed property value or null
     */
    public String removeProperty(final String name)
    {
        log.info("A property was removed: " + name);
        return Property.remove(name);
    }

    /**
     * Get an array style system property.
     *
     * @jmx:managed-operation
     * 
     * @param base          Base property name
     * @param defaultValues Default property values
     * @return              ArrayList of property values or default
     */
    public List getArray(final String base, final List defaultValues)
    {
        String[] array = new String[defaultValues.size()];
        defaultValues.toArray(array);
        String[] values = Property.getArray(base, array);
        return Arrays.asList(values);
    }

    /**
     * Get an array style system property.
     *
     * @jmx:managed-operation
     *
     * @param name       Property name
     * @return           ArrayList of property values or empty array
     */
    public List getArray(String name)
    {
        String[] array = Property.getArray(name);
        return Arrays.asList(array);
    }

    /**
     * Check if a system property of the given name exists.
     *
     * @jmx:managed-operation
     *
     * @param name    Property name
     * @return        True if property exists
     */
    public boolean doesPropertyExist(String name)
    {
        return Property.exists(name);
    }

    /**
     * Get a property group for under the given system property base.
     *
     * @jmx:managed-operation
     *
     * @param basename   Base property name
     * @return           Property group
     */
    public PropertyGroup getGroup(String basename)
    {
        return Property.getGroup(basename);
    }

    /**
     * Get a property group for under the given system property base
     * at the given index.
     *
     * @jmx:managed-operation
     *
     * @param basename   Base property name
     * @param index      Array property index
     * @return           Property group
     */
    public PropertyGroup getGroup(String basename, int index)
    {
        return Property.getGroup(basename, index);
    }

    /**
     * Add a property listener.
     *
     * @jmx:managed-operation
     *
     * @param listener   Property listener to add
     */
    public void addListener(final PropertyListener listener)
    {
        Property.addListener(listener);
    }

    /**
     * Add an array of property listeners.
     *
     * @jmx:managed-operation
     *
     * @param listeners     Array of property listeners to add
     */
    public void addListeners(final PropertyListener[] listeners)
    {
        Property.addListeners(listeners);
    }

    /**
     * Remove a property listener.
     *
     * @jmx:managed-operation
     *
     * @param listener   Property listener to remove
     * @return           True if listener was removed
     */
    public boolean removeListener(final PropertyListener listener)
    {
        return Property.removeListener(listener);
    }

    ///////////////////////////////////////////////////////////////////////////
    //                           Property Loading //
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Load some system properties from the given URL.
     *
     * @jmx:managed-operation
     *
     * @param url    The url to load properties from.
     */
    public void load(final URL url) throws IOException
    {
        log.debug("Loading system properties from: (" + url + ")");

        Properties props = System.getProperties();
        InputStream is = url.openConnection().getInputStream();
        props.load(is);
        is.close();
    }

    /**
     * Load some system properties from the given URL.
     * We also start the scheduled monitoring of this file.
     *
     * @jmx:managed-operation
     *
     * @param url    The url to load properties from.
     */
    public void loadAndAddTask(final String url) throws IOException,
            MalformedURLException
    {
        FileMonitorTask n = new FileMonitorTask(url, this);
        load( n.getUrl() );
        notifiers.add(n);
        // Engage scheduling of the file-monitoring
        timer.schedule(n, monitorInterval, monitorInterval);
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      JMX & Configuration Helpers //
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Construct and add a property listener.
     *
     * @jmx:managed-operation
     *
     * @param type   The type of property listener to add.
     */
    public void addListener(final String typename)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException
    {
        Class type = Class.forName(typename);
        PropertyListener listener = (PropertyListener)type.newInstance();

        addListener(listener);
    }

    /**
     * Load system properties for each of the given comma separated urls.
     *
     * @jmx:managed-attribute
     *
     * @param list   A list of comma separated urls.
     */
    public void setPropertyFileList(final String list)
            throws MalformedURLException, IOException
    {
        StringTokenizer stok = new StringTokenizer(list, ",");
        StringBuffer sb = new StringBuffer();
        sb.append("These Property files has been added:");

        // First remove all old propert-files
        removeAllPropertyFiles();
        // Then add all the ones we are assigned to have.
        while (stok.hasMoreTokens())
        {
            String url = stok.nextToken();
            loadAndAddTask(url);
            sb.append("\n" + url);
        }
        log.info(sb);
    }

    /**
     * Returns the list of property files we are monitoring in a comma-separated string.
     *
     * @jmx:managed-attribute
     *
     * @param list   A list of comma separated urls.
     * @return -	The list of property files we are monitoring in a comma-separated string.
     */
    public String getPropertyFileList() throws MalformedURLException,
            IOException
    {
        StringBuffer sb = new StringBuffer();

        Iterator i = notifiers.iterator();
        while (i.hasNext())
        {
            FileMonitorTask n = (FileMonitorTask)i.next();
            sb.append(n.getUri() + ",");
        }

        return sb.toString();
    }

    /**
     * Lists all monitored files currently active.
     * 
     * @return a list of the notifiers.
     */
    public String listPropertyFiles()
    {
        Iterator i = notifiers.iterator();
        StringBuffer result = new StringBuffer();
        while (i.hasNext())
        {
            FileMonitorTask n = (FileMonitorTask)i.next();
            result.append(n.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Stop all registered notifiers and clear the list of property-files we are
     * monitoring.
     */
    private void removeAllPropertyFiles()
    {
        log.debug("> removeAllPropertyFiles (" + notifiers.size() + ")");
        stopNotifiers();
        notifiers.clear();
        log.info("All Property files has been removed");
    }

    /** Set system properties by merging the given properties object. This will
     * replace valid references to properties of the form ${x} in 'props' or a
     * System property with the value of x.
     *
     * @jmx:managed-attribute
     *
     * @param props    Properties object to merge.
     */
    public void setProperties(final Properties props) throws IOException
    {
        log.info("Merging with system properties: (" + props + ")");
        System.getProperties().putAll(props);
    }

    /**
     * Return a Map of System.getProperties() with a toString implementation
     * that provides an html table of the key/value pairs.
     *
     * @jmx:managed-operation
     */
    public Map showAllProperties()
    {
        return new HTMLMap(System.getProperties());
    }

    /**
     * Return a Map of the property group for under the given system property base
     * with a toString implementation that provides an html table of the key/value pairs.
     *
     * @jmx:managed-operation
     *
     * @param basename   Base property name
     * @return           Property group
     */
    public Map showGroup(final String basename)
    {
        return new HTMLMap(getGroup(basename));
    }

    /**
     * A helper to render a map as HTML on toString()
     * 
     * <p>
     * The html adapter should in theory be able to render a map (nested map
     * list, array or whatever), but until then we can do it for it.
     */
    protected static class HTMLMap extends HashMap
    {

        public HTMLMap(final Map map)
        {
            super(map);
        }

        public String toString()
        {
            StringBuffer buff = new StringBuffer();

            buff.append("<table>");

            SortedSet keys = new TreeSet(this.keySet());
            Iterator iter = keys.iterator();
            while (iter.hasNext())
            {
                String key = (String)iter.next();
                buff.append("<tr><td align=\"left\"><b>").append(key).append(
                        "</b></td><td align=\"left\">").append(this.get(key)).append("</td></tr>\n\r");
            }

            buff.append("</table>");

            return buff.toString();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //                    ServiceMBeanSupport Overrides //
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Setup our reference to the server's home directory. This is done here
     * because one or more attribute setters makes use of this value.
     */
    public ObjectName preRegister(final MBeanServer server,
            final ObjectName name) throws Exception
    {
        // get server's home for relative paths, need this for making urls
        serverHome = ServerConfigLocator.locate().getServerHomeDir().getPath();

        return super.preRegister(server, name);
    }

    ///////////////////////////////////////////////////////////////////////////
    /**
     * The number of seconds between checking if the property-files has been
     * modified.
     * 
     * @return the period time in milliseconds.
     */
    public long getMonitorInterval()
    {
        return monitorInterval / 1000;
    }

    /**
     * The number of seconds between checking if the property-files has been
     * modified.
     * 
     * @param period	- the period time in milliseconds.
     */
    public void setMonitorInterval(long secs)
    {
        monitorInterval = secs * 1000;
        // We do NOT restart all notifiers, while this is done when we 
        // set the property-file list, which is done in the same call 
        // from the JMX-console.
    }

    /**
     * Adds a new file to be monitored.
     * 
     * @param uri	The filename (relative the jboss-path) where the file is located.
     */
    public void addPropertyFile(String uri)
    {
        try
        {
            loadAndAddTask(uri);
        }
        catch (MalformedURLException e)
        {
            log.warn("We got an exception when trying to add a new property-file, Exception: "
                            + e.toString());
        }
        catch (IOException e)
        {
            log.warn("We got an exception when trying to add a new property-file, Exception: "
                            + e.toString());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //                    Helper functions //
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Start all registered notifiers.
     */
    private void startNotifiers()
    {
        log.debug("> startNotifiers (" + notifiers.size() + ")");
        Iterator i = notifiers.iterator();
        while (i.hasNext())
        {
            FileMonitorTask n = (FileMonitorTask)i.next();
            //            n.run();
            timer.schedule(n, monitorInterval, monitorInterval);
        }
        log.info("All Property file notifiers has been started ("
                + notifiers.size() + ")");
    }

    /**
     * Stop all registered notifiers.
     */
    private void stopNotifiers()
    {
        log.debug("> stopNotifiers (" + notifiers.size() + ")");
        Iterator i = notifiers.iterator();
        while (i.hasNext())
        {
            FileMonitorTask n = (FileMonitorTask)i.next();
            n.cancel();
        }
        log.info("All Property file notifiers has been stopped ("
                + notifiers.size() + ")");
    }

    ///////////////////////////////////////////////////////////////////////////
    //                    Task helper class //
    ///////////////////////////////////////////////////////////////////////////

    /**
     * A timer with the responsibility to check the timestamp of a property-file
     * identified by an uri.
     */
    private class FileMonitorTask extends TimerTask
    {

        private URL url;
        String uri;

        private long timestamp = -1;

        private SystemPropertiesServiceMonitoring parent = null;

        public FileMonitorTask(String uri, SystemPropertiesServiceMonitoring parent) throws MalformedURLException
        {
            this.uri = uri;
         	url = Strings.toURL( uri, parent.getServerHome() );
            this.parent = parent;
            log.info("File monitoring task created for file  (" + url + ")");
        }

        public void run()
        {
            log.debug("Monitoring (" + url + ")");

            try
            {
                URLConnection connection = url.openConnection();
                long ts = connection.getLastModified();

                if (log.isTraceEnabled())
                {
                    log.debug("connection " + connection);
                    log.debug("timestamp: " + ts + ", old: " + timestamp);
                }

                if (ts == 0)
                {
                    log.info("unknown timestamp of Property-file (" + url + ")");
                    return;
                }

                if (ts > timestamp)
                {
                    timestamp = ts;
                    if (parent != null)
                    {
                        parent.load(url);
                        log.info("Property-file updated: (" + url + ")");
                    }
                    else
                    {
                        log.fatal("Failed to reload the updated Property-file: ("
                                        + url + ") while we have NO parent pointer !");
                    }
                }
            }
            catch (Exception e)
            {
                log.warn("unexpected error " + e);
            }
        }

        public String toString()
        {
            return "Property-file url=(" + url + ")";
        }
        /**
         * @return Returns the url.
         */
        public URL getUrl()
        {
            return url;
        }
        /**
         * @return Returns the uri.
         */
        public String getUri()
        {
            return uri;
        }
    }
    /**
     * @return Returns the serverHome.
     */
    public String getServerHome()
    {
        return serverHome;
    }
}