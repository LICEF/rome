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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.Parent;

import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.comete.CometeModule;
import com.rometools.rome.io.ModuleParser;

/**
 * @author Frederic Bergeron (frederic.bergeron@licef.ca)
 * Comete implementation of the ModuleParser class
 */
public class CometeModuleParser implements ModuleParser {
    
    private static final Namespace Comete_NS  = Namespace.getNamespace( "comete", CometeModule.URI );
    
    public String getNamespaceUri() {
        return CometeModule.URI;
    }

    public Module parse( Element dcRoot, Locale locale ) {

        // Not implemented yet.

        return null;
    }
    
}
