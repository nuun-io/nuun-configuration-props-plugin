/**
 * Copyright (C) 2013 Kametic <epo.jemba@kametic.com>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * or any later version
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nuun.plugin.configuration.props;

import io.nuun.kernel.api.di.UnitModule;
import io.nuun.kernel.api.plugin.InitState;
import io.nuun.kernel.api.plugin.PluginException;
import io.nuun.kernel.api.plugin.context.InitContext;
import io.nuun.kernel.api.plugin.request.ClasspathScanRequest;
import io.nuun.kernel.core.AbstractPlugin;
import io.nuun.kernel.spi.configuration.NuunBaseConfigurationPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import jodd.props.Props;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.google.inject.Module;

public class PropsConfigurationPlugin extends AbstractPlugin implements NuunBaseConfigurationPlugin
{

    Logger logger = LoggerFactory.getLogger(PropsConfigurationPlugin.class);
    
    
    private String PROPS_REGEX = ".*\\.props";
    
    
    private Module module = null;

    private Props props = null;

    
    public PropsConfigurationPlugin()
    {
    }
    
    @Override
    public void addConfiguration(Map<String, Object> configuration)
    {
        Properties p = new Properties();
        p.putAll(configuration);
    	props.load(p);
    	
    }
    
    @Override
    public Object getConfiguration()
    {
        return props;
    }

    @Override
    public String name()
    {
        return "nuun-props-configuration-plugin";
    }
    
    
    @Override
    public Object nativeUnitModule()
    {
        if (module == null )
        {
            module = new PropsConfigurationGuiceModule(props);
        }
        return module;
    }
    
    
    @Override
    public Collection<ClasspathScanRequest> classpathScanRequests()
    {
        return classpathScanRequestBuilder().resourcesRegex(PROPS_REGEX).build();
    }
    
    
    @Override
    public InitState init(InitContext initContext)
    {
     // find all properties classes in the classpath
        HashSet<String> allProperties = Sets.newHashSet();
        
        Collection<String> propertiesFiles = initContext.propertiesFiles();
        allProperties.addAll(propertiesFiles);
        
        // find all props
        Map<String, Collection<String>> mapResourcesByRegex = initContext.mapResourcesByRegex();
        Collection<String> propsFiles = mapResourcesByRegex.get(PROPS_REGEX);
        allProperties.addAll(propsFiles);

        props = new Props();
        
        for (String propertiesFile : allProperties)
        {
            logger.info("adding {} to configuration", propertiesFile);
            
            try
            {
                InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream( propertiesFile);
                
                props.load(resourceAsStream);
            }
            catch (IOException e)
            {
                throw new PluginException("Error When loading properties file " + propertiesFile);
            }
        }
        
        
        return InitState.INITIALIZED;
    }
    
    
}
