/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
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
package com.rometools.rome.feed.module.comete;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.comete.impl.util.LangString;

/**
 * Subject of the Dublin Core ModuleImpl.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 * @author Alejandro Abdelnur
 *
 * Addition of LangString
 * Addition of topElementName and topElementNamespace
 * @author Frederic Bergeron
 *
 */
public interface DCSubject extends Cloneable,CopyFrom {
    /**
     * Returns the top element name.
     * <p>
     * @return the top element name.  By default, it is "subject".
     *
     */
    String getTopElementName();

    /**
     * Sets the top element name.
     * <p>
     * @param name the top element name into which the data will be contained.
     *
     */
    void setTopElementName(String name);

    /**
     * Returns the top element namespace uri.
     * <p>
     * @return the top element namespace uri.  By default, it is "http://purl.org/dc/elements/1.1/".
     *
     */
    String getTopElementNamespaceUri();

    /**
     * Sets the top element namespace uri.
     * <p>
     * @param ns the top element namespace uri into which the data will be contained.
     *
     */
    void setTopElementNamespaceUri(String nsUri);

    /**
     * Returns the top element namespace prefix.
     * <p>
     * @return the top element namespace prefix.  By default, it is "http://purl.org/dc/elements/1.1/".
     *
     */
    String getTopElementNamespacePrefix();

    /**
     * Sets the top element namespace prefix.
     * <p>
     * @param ns the top element namespace prefix into which the data will be contained.
     *
     */
    void setTopElementNamespacePrefix(String nsPrefix);

    /**
     * Returns the DublinCore subject taxonomy URI.
     * <p>
     * @return the DublinCore subject taxonomy URI, <b>null</b> if none.
     *
     */
    String getTaxonomyUri();

    /**
     * Sets the DublinCore subject taxonomy URI.
     * <p>
     * @param taxonomyUri the DublinCore subject taxonomy URI to set, <b>null</b> if none.
     *
     */
    void setTaxonomyUri(String taxonomyUri);

    /**
     * Returns the DublinCore subject value.
     * <p>
     * @return the DublinCore subject value, <b>null</b> if none.
     *
     */
    LangString getValue();

    /**
     * Sets the DublinCore subject value.
     * <p>
     * @param value the DublinCore subject value to set, <b>null</b> if none.
     *
     */
    void setValue(LangString value);

    /**
     * Returns the DublinCore subject value.
     * <p>
     * @return the DublinCore subject value, <b>null</b> if none.
     *
     */
    String getIdentifier();

    /**
     * Sets the DublinCore subject value.
     * <p>
     * @param value the DublinCore subject value to set, <b>null</b> if none.
     *
     */
    void setIdentifier(String value);

    /**
     * Creates a deep clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

}
