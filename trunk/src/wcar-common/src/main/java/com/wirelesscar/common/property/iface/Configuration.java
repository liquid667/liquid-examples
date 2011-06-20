/*
 * Copyright (c) 2003-2004 WirelessCar. All Rights Reserved.
 *
 * This SOURCE CODE FILE, which has been provided by WirelessCar as part
 * of a WirelessCar product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of WirelessCar.
 *
 */
package com.wirelesscar.common.property.iface;

/**
 * A <code>Configuration</code> keeps configuration data for the system that can be read. 
 * How these properties are stored in the system is up to each implementing class.
 * 
 * @author Niclas Nilsson
 * @version $Revision: 1.1 $
 */
public interface Configuration
{
    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public String get(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public String get(String key, String def);

    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public Byte getByte(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Byte getByte(String key, Byte def);
    
    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public Short getShort(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Short getShort(String key, Short def);
    
    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public Integer getInteger(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Integer getInteger(String key, Integer def);

    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public Long getLong(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Long getLong(String key, Long def);
    
    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public Boolean getBoolean(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Boolean getBoolean(String key, Boolean def);
    
    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public Float getFloat(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Float getFloat(String key, Float def);

    /**
     * Returns a configuration string matching the given key.
     * @param key the name of the value
     * @return the configured value
     */
    public Double getDouble(String key);
    
    /**
     * Returns a configuration string matching the given key. If no value matched the
     * given key, the supplied defaultvalue is returned.
     * @param key the name of the value
     * @param def the value to return if no value matched the key
     * @return the configured value of the supplied defaultvalue.
     */
    public Double getDouble(String key, Double def);
    
}