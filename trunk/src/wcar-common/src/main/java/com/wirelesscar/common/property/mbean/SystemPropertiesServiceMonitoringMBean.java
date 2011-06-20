/*
 * Created on Jun 17, 2004
 * 
 * Copyright (c) 2003 WirelessCar. All Rights Reserved.
 * 
 * This SOURCE CODE FILE, which has been provided by WirelessCar as part
 * of a WirelessCar product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of WirelessCar.
 * 
 * Last change made by: $Author: mikael $ at $Date: 2004/06/18 08:38:55 $
 * @version $Revision: 1.2 $
 */
package com.wirelesscar.common.property.mbean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;

/**
 * A service to load properties from property-files an place 
 * them in the system properties.
 * The property-files are monitored for changes, so when a file 
 * has been changed, these properties are reloaded.
 * 
 * @author Mikael Thorman
 * 
 * @version $Revision: 1.2 $
 */
public interface SystemPropertiesServiceMonitoringMBean
{
	/**
	 * Lists all notifiers currently active.
	 * 
	 * @return a list of the notifiers.
	 */
	public String listPropertyFiles();
	 
	/**
	 * Adds a new notifier.
	 * 
	 * @param uri        The filename (relative the jboss-path) where the file is located. 
	 */
	public void addPropertyFile(String uri);
	
    /**
     * Load system properties for each of the given comma separated urls.
     *
     * @param list   A list of comma separated urls.
     */
    public void setPropertyFileList(final String list) throws MalformedURLException, IOException;

    /**
     * Returns the list of property files we are monitoring in a comma-separated string.
     *
     * @param list   A list of comma separated urls.
     * @return -	The list of property files we are monitoring in a comma-separated string.
     */
    public String getPropertyFileList() throws MalformedURLException, IOException;

//===================================================
//	Timer functions
    
	/**
	 * The number of seconds between checking if the property-files has been modified. 
	 * 
	 * @return the period time in milliseconds.
	 */
	public long getMonitorInterval();
	  
	/**
	 * The number of seconds between checking if the property-files has been modified.
	 * 
	 * @param period the period time in milliseconds.
	 */
	public void setMonitorInterval(long secs);
	
//	===================================================
//	Properties functions
	
    /** Set system properties by merging the given properties object. This will
     * replace valid references to properties of the form ${x} in 'props' or a
     * System property with the value of x.
     *
     * @param props    Properties object to merge.
     */
    public void setProperties(final Properties props) throws IOException;
    
    /**
     * Set a system property.
     *
     * @param name    The name of the property to set.
     * @param value   The value of the property.
     * @return        Previous property value or null
     */
    public String setProperty(final String name, final String value);

    /**
     * Get a system property.
     * 
     * @param name          Property name
     * @param defaultValue  Default property value
     * @return              Property value or default
     */
    public String getProperty(final String name, final String defaultValue);

    /**
     * Get a system property.
     *
     * @param name       Property name
     * @return           Property value or null
     */
    public String getProperty(final String name);

    /**
     * Remove a system property.
     *
     * @param name    The name of the property to remove.
     * @return        Removed property value or null
     */
    public String removeProperty(final String name);

    /**
     * Check if a system property of the given name exists.
     *
     * @param name    Property name
     * @return        True if property exists
     */
    public boolean doesPropertyExist(String name);

    /**
     * Return a Map of System.getProperties() with a toString implementation
     * that provides an html table of the key/value pairs.
     */
    public Map showAllProperties();
}
