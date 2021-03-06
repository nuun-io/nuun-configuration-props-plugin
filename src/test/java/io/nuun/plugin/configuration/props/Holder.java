/**
 * Copyright (C) 2013-2014 Kametic <epo.jemba@kametic.com>
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

import io.nuun.kernel.spi.configuration.NuunConfigurationConverter;
import io.nuun.kernel.spi.configuration.NuunProperty;
import io.nuun.plugin.log.NuunLog;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

public class Holder
{

    @NuunLog
    private Logger myLogger;



    
    @Inject
    @Named("kernelmodule")
    public String moduleAnno;
    
    
    public Holder()
    {
    }




    @NuunProperty("user.id")
    public Long      id;

    @NuunProperty("private1")
    private String    private1;
    
    @NuunProperty("private2")
    private String    private2;
    
    public String private1()
    {
        return private1;
    }
    
    public String private2()
    {
        return private2;
    }

    @NuunProperty("user.password")
    public String    password;

    @NuunProperty("test.int")
    public int       _int;
    
    @NuunProperty("test.int")
    public String    __Integer;

    @NuunProperty("test.long")
    public long      _long;
    @NuunProperty("test.long")
    public Long      _Long;
    @NuunProperty("test.long")
    public String    __Long;

    @NuunProperty("test.boolean.true")
    public boolean   _booleanTrue;
    @NuunProperty("test.boolean.true")
    public Boolean   _BooleanTrue;
    @NuunProperty("test.boolean.true")
    public String    __BooleanTrue;

    @NuunProperty("test.boolean.false")
    public boolean   _booleanFalse;
    @NuunProperty("test.boolean.false")
    public Boolean   _BooleanFalse;
    @NuunProperty("test.boolean.false")
    public String    __BooleanFalse;

    @NuunProperty("test.short")
    public short     _short;
    @NuunProperty("test.short")
    public Short     _Short;
    @NuunProperty("test.short")
    public String    __Short;

    @NuunProperty("test.byte")
    public byte      _byte;
    @NuunProperty("test.byte")
    public Byte      _Byte;
    @NuunProperty("test.byte")
    public String    __Byte;

    @NuunProperty("test.float")
    public float     _float;
    @NuunProperty("test.float")
    public Float     _Float;
    @NuunProperty("test.float")
    public String    __Float;

    @NuunProperty("test.double")
    public double    _double;
    @NuunProperty("test.double")
    public Double    _Double;
    @NuunProperty("test.double")
    public String    __Double;

    @NuunProperty("test.char")
    public char      _char;
    @NuunProperty("test.char")
    public Character _Character;
    @NuunProperty("test.char")
    public String    __Character;
    
    @NuunProperty(value = "test.convert" , converter = MySpecialConverter.class)
    public MySpecialObject __special;
    
    public static class MySpecialObject
    {
        public final String[] content;

        public MySpecialObject(String[] content)
        {
            this.content = content;
        }
    }
    
    public static class MySpecialConverter implements NuunConfigurationConverter<MySpecialObject>
    {
        @Override
        public MySpecialObject convert(String property)
        {
            
            String[] split = property.split(" ");
            
            return new MySpecialObject(split);
        }
    }
    
    // Here the key test.no did not exist and must be flagged as madatory = false;
    @NuunProperty(value = "test.no" , mandatory = false)
    public String    __nothing;

    public Logger getLogger()
    {
        return myLogger;
    }

}
