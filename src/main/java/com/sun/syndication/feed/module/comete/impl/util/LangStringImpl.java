/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sun.syndication.feed.module.comete.impl.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.impl.CopyFromHelper;

/**
 * @author Frederic Bergeron (frederic.bergeron@licef.ca)
 * Class representation of a LangString.
 */
public class LangStringImpl implements Cloneable, Serializable, LangString {
    ObjectBean _objBean = null;
    
    private String string;
    private String lang;
    
    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     */
    public LangStringImpl() {
        _objBean = new ObjectBean(this.getClass(),this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        return _objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _objBean.toString();
    }
    
    /**
     * @return Returns the string.
     */
    public String getString() {
        return string;
    }

    /**
     * @param string The string of the LangString.
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * @return Returns the language.
     */
    public String getLanguage() {
        return lang;
    }

    /**
     * @param language The language of the LangString.
     */
    public void setLanguage(String language) {
        this.lang = language;
    }

    public Class getInterface() {
        return LangString.class;
    }

    public void copyFrom(CopyFrom obj) {
        COPY_FROM_HELPER.copy(this,obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("string",String.class);
        basePropInterfaceMap.put("language",String.class);

        Map basePropClassImplMap = Collections.EMPTY_MAP;

        COPY_FROM_HELPER = new CopyFromHelper(LangString.class,basePropInterfaceMap,basePropClassImplMap);
    }

}
