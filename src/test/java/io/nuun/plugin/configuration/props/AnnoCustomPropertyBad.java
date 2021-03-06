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
import io.nuun.kernel.spi.configuration.NuunDummyConverter;
import io.nuun.kernel.spi.configuration.NuunProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NuunProperty(value = "")
public @interface AnnoCustomPropertyBad
{
    String value();
    boolean mandatory() default true;
    String defaultValue() default "";
    byte defaultByteValue() default 0;
    short defaultShortValue() default 0;
    int defaultIntValue() default 0;
    long defaultLongValue() default 0;
    float defaultFloatValue() default 0;
    double defaultDoubleValue() default 0.0;
    boolean defaultBooleanValue_() default false; // here we add an underscore to brake the equivalence.
    Class<? extends NuunConfigurationConverter<?>> converter() default NuunDummyConverter.class;
}