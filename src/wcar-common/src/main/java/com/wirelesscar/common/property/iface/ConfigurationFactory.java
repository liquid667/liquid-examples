/*
 *
 */
package com.wirelesscar.common.property.iface;

import com.wirelesscar.common.property.impl.SystemPropertiesConfiguration;

/**
 * A factory class that creates Configuration objects.
 *
 * @author Niclas Nilsson
 * @version $Revision: 1.1 $
 */
public class ConfigurationFactory 
{
    private static Configuration configuration;

    /**
     * @return a Configuration object to use.
     */
    public static Configuration get()
    {
        if (configuration == null)
        {
            configuration = new SystemPropertiesConfiguration();
        }
        
        return configuration;
    }
    
}
