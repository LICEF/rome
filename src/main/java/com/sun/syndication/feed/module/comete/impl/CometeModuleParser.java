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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.Parent;

import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.comete.CometeModule;
import com.sun.syndication.io.ModuleParser;

/**
 * @author Frederic Bergeron (frederic.bergeron@licef.ca)
 * Comete implementation of the ModuleParser class
 */
public class CometeModuleParser implements ModuleParser {
    
    private static final Namespace Comete_NS  = Namespace.getNamespace( "comete", CometeModule.URI );
    
    public String getNamespaceUri() {
        return CometeModule.URI;
    }

    public Module parse( Element dcRoot ) {
        URL baseURI = findBaseURI( dcRoot );
        
        boolean foundSomething = false;
        CometeModule cm = new CometeModuleImpl();

        List extraInfos = dcRoot.getChildren("extraInfo", Comete_NS);
        
        if(extraInfos != null && extraInfos.size() > 0){
            
            List extraInfoList = new LinkedList();
            
            for (Iterator iter = extraInfos.iterator(); iter.hasNext();) {
                e = (Element) iter.next();
                extraInfoList.add( parseExtraInfo( e ) );
            }
        
            cm.setExtraInfos( extraInfoList );
        }
        
        return (foundSomething) ? cm : null;
    }
    
    private static String parseExtraInfo( Element e ){
       
        // Not implemented yet.
        
        return( null );
    }
    
}
