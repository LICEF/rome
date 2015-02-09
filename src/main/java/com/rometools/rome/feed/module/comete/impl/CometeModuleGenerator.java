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

package com.rometools.rome.feed.module.comete.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.comete.CometeModule;
import com.rometools.rome.feed.module.comete.impl.util.LangString;
import com.rometools.rome.io.ModuleGenerator;
import com.rometools.rome.io.impl.DateParser;

/**
 * @author Frederic Bergeron (frederic.bergeron@licef.ca)
 * Comete implementation of ModuleGenerator
 */
public class CometeModuleGenerator  implements ModuleGenerator {
    
    private static final Namespace Comete_NS = Namespace.getNamespace( "comete", CometeModule.URI );

    public String getNamespaceUri() {
        return CometeModule.URI;
    }

    private static final Set NAMESPACES;

    static {
        Set nss = new HashSet();
        nss.add(Comete_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in
     * the root element of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    public Set getNamespaces() {
        return NAMESPACES;
    }

    public void generate(Module module, Element element) {

        CometeModule cm = (CometeModule)module;

        if(cm.getExtraInfos() != null) {
            List extraInfos = cm.getExtraInfos();
            for( Iterator it = extraInfos.iterator(); it.hasNext(); ) {
                LangString langString = (LangString) it.next();
                if( langString != null)
                    element.addContent( generateLangStringElement( "extraInfo", langString ) );
            }
        }           

        if(cm.getKeywords() != null) {
            List extraInfos = cm.getKeywords();
            for( Iterator it = extraInfos.iterator(); it.hasNext(); ) {
                LangString langString = (LangString) it.next();
                if( langString != null)
                    element.addContent( generateLangStringElement( "keyword", langString ) );
            }
        }           

        if(cm.getAdded() != null )
            element.addContent( generateDateElement( "added", cm.getAdded() ) );

        if(cm.getUpdated() != null )
            element.addContent( generateDateElement( "updated", cm.getUpdated() ) );
    }

    protected Element generateLangStringElement( String tagName, LangString langString ) {
        Element qElement = new Element( tagName, Comete_NS );
        if( langString.getLanguage() != null && !"".equals( langString.getLanguage() ) )
            qElement.setAttribute( "lang", langString.getLanguage(), Namespace.XML_NAMESPACE );
        qElement.setText( langString.getString() );
        
        return qElement;
    }
    
    protected Element generateDateElement( String tagName, Date date ) {
        Element qElement = new Element( tagName, Comete_NS );
        qElement.addContent( DateParser.formatW3CDateTime( date, Locale.FRENCH ) );
        return qElement;
    }
    
}
