/*
 * Copyright (c) 2003-2004 WirelessCar. All Rights Reserved.
 *
 * This SOURCE CODE FILE, which has been provided by WirelessCar as part
 * of a WirelessCar product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of WirelessCar.
 *
 */
package com.wirelesscar.common.property.impl;

import com.wirelesscar.common.property.iface.Configuration;

/**
 * A <code>SystemPropertiesConfiguration</code> is an &qout;instance aware&qout; implementation
 * of <code>Configuration</code> based on the Java system properties. &qout;Instance aware&qout; means that
 * even if all systems for convenience may equipped with a superset of all configuration data in the
 * system properties, the <code>SystemPropertiesConfiguration</code> is able to filter out data meant for 
 * this specific system instance.
 * <p>
 * The instancename is given to a system instance by setting the System property 
 * &qout;wirelesscar.jboss.instance&qout;.
 * <p>
 * <em>Example:</em> Imagine that we have a set of system properties like these:
 * <code>
 *     com.dynafleet.dbtype=oracle
 *     com.dynafleet.dbip=localhost
 *     com.dynafleet.dbip.machine1=192.168.12.190
 *     com.dynafleet.dbip.machine2=172.24.85.80
 * </code>
 * When a piece of code asks for the <code>com.dynafleet.dbtype</code> property, it will get
 * the answer <code>oracle</code> no matter which instancename it has. If a machine with no instancename
 * asks for the property <code>com.dynafleet.dbip</code>, it will get the answer <code>localhost</code>.
 * A system with the instancename <code>machine7</code> will get the answer <code>localhost</code>.
 * However, if a machine with the instancename <code>machine1</code> asks for the same property, it
 * will get the &qout;personilized&qout; answer <code>192.168.12.190</code> instead of <code>localhost</code>.
 * 
 * @author Niclas Nilsson
 * @version $Revision: 1.1 $
 */
public class SystemPropertiesConfiguration implements Configuration 
{
    private String me = System.getProperty("wirelesscar.jboss.instance");
    
    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public String get(String key)
    {
        String value = null;
        
        if (me != null)
        {    
            String meKey = key + "." + me;
            value = System.getProperty(meKey);
        }
        
        if (value == null)
        {
            value = System.getProperty(key);
        }
        
        return value;
    }
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public String get(String key, String def)
    {
        String value = get(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }
    
    /**
     * Returns a configured value as a Boolean.
     * 
     * @param key the name of the value
     * @return the configured value
     */
    public Boolean getBoolean(String key)
    {
        Boolean result = null;
        
        String value = get(key);
        
        if (value != null)
        {    
            String lowerCaseValue = value.toLowerCase();
            
            if (lowerCaseValue.equals("true"))
            {
                result = Boolean.TRUE;
            }
            else if (lowerCaseValue.equals("false"))
            {
                result = Boolean.FALSE;
            }
        }
        
        return result;
    }
    
    /**
     * Returns a configured value or a default value as a Boolean.
     * 
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Boolean getBoolean(String key, Boolean def)
    {
        Boolean value = getBoolean(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }
    
    /**
     * Returns a configured value as an Integer.
     * 
     * @param key the name of the value
     * @return the configured value
     */
    public Integer getInteger(String key)
    {
        Integer result = null;
        
        String value = get(key);
        
        if (value != null)
        {    
            try 
            {
                result = new Integer(value);
            }
            catch (NumberFormatException e)
            {
                result = null;
            }
        }

        return result;
    }
    
    /**
     * Returns a configured value or a default value as an Integer.
     * 
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Integer getInteger(String key, Integer def)
    {
        Integer value = getInteger(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }
    
    /**
     * Returns a configured value as a Byte.
     * 
     * @param key the name of the value
     * @return the configured value
     */
    public Byte getByte(String key)
    {
        Byte result = null;
        
        String value = get(key);
        
        if (value != null)
        {    
            try 
            {
                result = new Byte(value);
            }
            catch (NumberFormatException e)
            {
                result = null;
            }
        }
        
        return result;
    }
    
    /**
     * Returns a configured value or a default value as a Byte.
     * 
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Byte getByte(String key, Byte def)
    {
        Byte value = getByte(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }

    /**
     * Returns a configured value as a Short.
     * 
     * @param key the name of the value
     * @return the configured value
     */
    public Short getShort(String key)
    {
        Short result = null;
        
        String value = get(key);
        
        if (value != null)
        {    
            try 
            {
                result = new Short(value);
            }
            catch (NumberFormatException e)
            {
                result = null;
            }
        }
        
        return result;
    }
    
    /**
     * Returns a configured value or a default value as a Short.
     * 
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Short getShort(String key, Short def)
    {
        Short value = getShort(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }

    /**
     * Returns a configured value as a Long.
     * 
     * @param key the name of the value
     * @return the configured value
     */
    public Long getLong(String key)
    {
        Long result = null;
        
        String value = get(key);
        
        if (value != null)
        {    
            try 
            {
                result = new Long(value);
            }
            catch (NumberFormatException e)
            {
                result = null;
            }
        }
        
        return result;
    }
    
    /**
     * Returns a configured value or a default value as a Long.
     * 
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Long getLong(String key, Long def)
    {
        Long value = getLong(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }
    
    /**
     * Returns a configured value as a Float.
     * 
     * @param key the name of the value
     * @return the configured value
     */
    public Float getFloat(String key)
    {
        Float result = null;
        
        String value = get(key);
        
        if (value != null)
        {    
            try 
            {
                result = new Float(value);
            }
            catch (NumberFormatException e)
            {
                result = null;
            }
        }
        
        return result;
    }
    
    /**
     * Returns a configured value or a default value as a Float.
     * 
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Float getFloat(String key, Float def)
    {
        Float value = getFloat(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }
    
    /**
     * Returns a configured value as a Double.
     * 
     * @param key the name of the value
     * @return the configured value
     */
    public Double getDouble(String key)
    {
        Double result = null;
        
        String value = get(key);
        
        if (value != null)
        {    
            try 
            {
                result = new Double(value);
            }
            catch (NumberFormatException e)
            {
                result = null;
            }
        }
        
        return result;
    }
    
    /**
     * Returns a configured value or a default value as a Double.
     * 
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Double getDouble(String key, Double def)
    {
        Double value = getDouble(key);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }
 }



