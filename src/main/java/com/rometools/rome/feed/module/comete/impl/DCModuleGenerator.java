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
package com.rometools.rome.feed.module.comete.impl;

import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.comete.DCModule;
import com.rometools.rome.feed.module.comete.DCSubject;
import com.rometools.rome.feed.module.comete.impl.util.LangString;
import com.rometools.rome.io.ModuleGenerator;
import com.rometools.rome.io.impl.DateParser;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;


/**
 * Feed Generator for DublinCore Module.
 * <p/>
 *
 * @author Elaine Chien
 *
 * Addition of LangString
 * @author Frederic Bergeron
 *
 */
public class DCModuleGenerator implements ModuleGenerator {

    private static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    private static final String DC_URI  = "http://purl.org/dc/elements/1.1/";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";
    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String SKOS_URI = "http://www.w3.org/2004/02/skos/core#";

    private static final Namespace XML_NS = Namespace.getNamespace("xml", XML_URI);
    private static final Namespace DC_NS  = Namespace.getNamespace("dc", DC_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace("taxo", TAXO_URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);
    private static final Namespace SKOS_NS = Namespace.getNamespace("skos", SKOS_URI);

    private static final Set<Namespace> NAMESPACES;

    static {
        Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(DC_NS);
        nss.add(TAXO_NS);
        nss.add(RDF_NS);
        nss.add(SKOS_NS);
        nss.add(XML_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    public final String getNamespaceUri() {
        return DC_URI;
    }
    
    private final Namespace getDCNamespace() {
        return DC_NS;
    }

    private final Namespace getRDFNamespace() {
        return RDF_NS;
    }

    private final Namespace getTaxonomyNamespace() {
        return TAXO_NS;
    }

    private final Namespace getSkosNamespace() {
        return SKOS_NS;
    }

    private final Namespace getXmlNamespace() {
        return XML_NS;
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module
     * generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition
     * in the root element of the generated document (forward-missing of
     * Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs this module generator uses.
     */
    public final Set getNamespaces() {
        return NAMESPACES;
    }

    /**
     * Populate an element tree with elements for a module.
     * <p>
     * @param module the module to populate from.
     * @param element the root element to attach child elements to.
     */
    public final void generate(Module module, Element element) {
        // This test is needed because we override the DCModule with the same URI. - FB
        if( module instanceof DCModule ) {
            DCModule dcModule = (DCModule) module;

            if (dcModule.getTitle() != null) {
                //element.addContent(generateSimpleElementList("title", dcModule.getTitles()));
                List<LangString> titles = dcModule.getTitles();
                
                for( Iterator<LangString> it = titles.iterator(); it.hasNext(); ) {
                    LangString langString = it.next();
                    if( langString != null)
                        element.addContent( generateLangStringElement( "title", langString ) );
                }
            }
            if (dcModule.getCreator() != null) {
                element.addContent(generateSimpleElementList("creator", dcModule.getCreators()));
            }
            List<DCSubject> subjects = dcModule.getSubjects();
            if (subjects.size() > 0) {
                Element taxos = new Element("topics", getTaxonomyNamespace());
                element.addContent(taxos);
                Element bag = new Element("Bag", getRDFNamespace());
                taxos.addContent(bag);
                for (int i = 0; i < subjects.size(); i++) {
                    DCSubject subject = subjects.get(i);
                    if (subject.getTaxonomyUri() != null) {
                        bag.addContent(generateTaxoLinkElement(subject));
                        element.addContent(generateSkosSubjectElement(subject));
                    } else {
                        element.addContent(generateSimpleSubjectElement(subject));
                    }
                }
            }
            if (dcModule.getDescription() != null) {
                //element.addContent(generateSimpleElementList("description", dcModule.getDescriptions()));
                List<LangString> descriptions = dcModule.getDescriptions();
                
                for( Iterator<LangString> it = descriptions.iterator(); it.hasNext(); ) {
                    LangString langString = it.next();
                    if( langString != null)
                        element.addContent( generateLangStringElement( "description", langString ) );
                }
            }
            if (dcModule.getPublisher() != null) {
                element.addContent(generateSimpleElementList("publisher", dcModule.getPublishers()));
            }
            if (dcModule.getContributors() != null) {
                element.addContent(generateSimpleElementList("contributor", dcModule.getContributors()));
            }
            if (dcModule.getDate() != null) {
                for (Iterator<Date> i = dcModule.getDates().iterator(); i.hasNext();) {
                    element.addContent(generateSimpleElement("date",
                            DateParser.formatW3CDateTime(i.next(), Locale.getDefault())));
                }
            }
            if (dcModule.getType() != null) {
                element.addContent(generateSimpleElementList("type", dcModule.getTypes()));
            }
            if (dcModule.getFormat() != null) {
                element.addContent(generateSimpleElementList("format", dcModule.getFormats()));
            }
            if (dcModule.getIdentifier() != null) {
                element.addContent(generateSimpleElementList("identifier", dcModule.getIdentifiers()));
            }
            if (dcModule.getSource() != null) {
                element.addContent(generateSimpleElementList("source", dcModule.getSources()));
            }
            if (dcModule.getLanguage() != null) {
                element.addContent(generateSimpleElementList("language", dcModule.getLanguages()));
            }
            if (dcModule.getRelation() != null) {
                element.addContent(generateSimpleElementList("relation", dcModule.getRelations()));
            }
            if (dcModule.getCoverage() != null) {
                element.addContent(generateSimpleElementList("coverage", dcModule.getCoverages()));
            }
            if (dcModule.getRights() != null) {
                element.addContent(generateSimpleElementList("rights", dcModule.getRightsList()));
            }
        }
    }

    /**
     * Utility method to generate an element for a subject.
     * <p>
     * @param subject the subject to generate an element for.
     * @return the element for the subject.
     */
    protected final Element generateSkosSubjectElement(DCSubject subject) {
        Element subjectElement = new Element("subject", getDCNamespace());
        Element conceptElement = new Element("Concept", getSkosNamespace());
        Attribute resourceAttribute = new Attribute("about", subject.getIdentifier(), getRDFNamespace());
        conceptElement.setAttribute(resourceAttribute);

        Element typeElement = new Element("type", getRDFNamespace());
        typeElement.addContent(SKOS_URI + "Concept");
        conceptElement.addContent(typeElement);

        Element schemeElement = new Element("inScheme", getSkosNamespace());
        Attribute taxoAttribute = new Attribute("about", subject.getTaxonomyUri(), getRDFNamespace());
        schemeElement.setAttribute(taxoAttribute);
        conceptElement.addContent(schemeElement);

        if (subject.getValue() != null) {
            LangString langString = subject.getValue();
            Element labelElement = new Element("prefLabel", getSkosNamespace());
            if( langString.getLanguage() != null && !"".equals( langString.getLanguage() ) )
                labelElement.setAttribute( "lang", langString.getLanguage(), Namespace.XML_NAMESPACE );
            labelElement.addContent(langString.getString());
            conceptElement.addContent(labelElement);
        }
        subjectElement.addContent(conceptElement);
        return subjectElement;
    }

    /**
     * Utility method to generate an element for a subject.
     * <p>
     * @param subject the subject to generate an element for.
     * @return the element for the subject.
     */
    protected final Element generateSimpleSubjectElement(DCSubject subject) {
        Element subjectElement = new Element("subject", getDCNamespace());
        LangString langString = subject.getValue();
        if( langString.getLanguage() != null && !"".equals( langString.getLanguage() ) )
            subjectElement.setAttribute( "lang", langString.getLanguage(), Namespace.XML_NAMESPACE );
        subjectElement.addContent(langString.getString());
        return subjectElement;
    }

    /**
     * Utility method to generate an element for a subject.
     * <p>
     * @param subject the subject to generate an element for.
     * @return the element for the subject.
     */
    protected final Element generateTaxoLinkElement(DCSubject subject) {
        Element subjectElement = new Element("li", getRDFNamespace());
        subjectElement.setAttribute(new Attribute("resource", subject.getIdentifier()));
        return subjectElement;
    }

    /**
     * Utility method to generate a single element containing a string.
     * <p>
     * @param name the name of the elment to generate.
     * @param value the value of the text in the element.
     * @return the element generated.
     */
    protected final Element generateSimpleElement(String name, String value)  {
        Element element = new Element(name, getDCNamespace());
        element.addContent(value);

        return element;
    }

    /**
     * Utility method to generate a list of simple elements.
     * <p>
     * @param name the name of the element list to generate.
     * @param value the list of values for the elements.
     * @return a list of Elements created.
     */
    protected final List<Element> generateSimpleElementList(String name, List value) {
        List<Element> elements = new ArrayList<Element>();
        for (Iterator i = value.iterator(); i.hasNext();) {
            elements.add(generateSimpleElement(name, (String) i.next()));
        }

        return elements;
    }
    
    protected Element generateLangStringElement( String tagName, LangString langString ) {
        Element qElement = new Element( tagName, getDCNamespace() );
        if( langString.getLanguage() != null && !"".equals( langString.getLanguage() ) )
            qElement.setAttribute( "lang", langString.getLanguage(), Namespace.XML_NAMESPACE );
        qElement.setText( langString.getString() );
        
        return qElement;
    }
    
}
