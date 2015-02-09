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

package com.sun.syndication.feed.module.comete.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.comete.CometeModule;
import com.sun.syndication.io.ModuleGenerator;

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

        if(cm.getExtraInfos() != null){
            
            List extraInfos = cm.getExtraInfos();
            
            for( Iterator it = extraInfos.iterator(); it.hasNext(); ) {
                String extraInfo = (String) it.next();
                if( extraInfo != null) {
                    element.addContent( generateExtraInfoElement( extraInfo ) );
                }
            }
        }           
    }

    protected Element generateExtraInfoElement( String extraInfo ) {
        
        Element qElement = new Element( "extraInfo", Comete_NS );
        qElement.setText( extraInfo );
        
        return qElement;
    }
    
}
