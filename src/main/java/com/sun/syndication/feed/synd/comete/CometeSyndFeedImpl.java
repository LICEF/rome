package com.sun.syndication.feed.synd.comete;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.sun.syndication.feed.module.comete.DCSubject;
import com.sun.syndication.feed.module.comete.impl.util.LangString;
import com.rometools.rome.feed.synd.SyndFeedImpl;

public class CometeSyndFeedImpl extends SyndFeedImpl implements CometeSyndFeed {
   private static final long serialVersionUID = 4003147068402456878L;
    private static final String DC_URI  = "http://purl.org/dc/elements/1.1/";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";
    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private static final Namespace DC_NS  = Namespace.getNamespace("dc", DC_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace("taxo", TAXO_URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);

   List<DCSubject> _topics;
   
    private final Namespace getDCNamespace() {
        return DC_NS;
    }

    private final Namespace getRDFNamespace() {
        return RDF_NS;
    }

    private final Namespace getTaxonomyNamespace() {
        return TAXO_NS;
    }

   @Override
   public List<DCSubject> getTopics() {
       return _topics;
   }

   @Override
   public void setTopics(List<DCSubject> topics) {
       _topics = topics;
   }
   public List<Element> getForeignMarkup() {
       List<Element> markup = new ArrayList<Element>();
       /*
        * Goal:
         <taxo:topic rdf:about="http://meerkat.oreillynet.com/?c=cat23">
           <taxo:link>http://meerkat.oreillynet.com/?c=cat23</taxo:link>
           <dc:title xml:lang="fr">Data: XML</taxo:title>
         </taxo:topic>
       */
       for (int i = 0; i < _topics.size(); i++) {
           DCSubject subject = _topics.get(i);
           Element topic = new Element("topic", getTaxonomyNamespace());
           topic.setAttribute("about", subject.getIdentifier(), getRDFNamespace());
           Element link = new Element("link", getTaxonomyNamespace());
           topic.addContent(link);
           link.addContent(subject.getIdentifier());
           Element title = new Element("title", getDCNamespace());
           topic.addContent(title);
           LangString langString = subject.getValue();
           title.addContent(langString.getString());
            if( langString.getLanguage() != null && !"".equals( langString.getLanguage() ) )
                title.setAttribute( "lang", langString.getLanguage(), Namespace.XML_NAMESPACE );

           markup.add(topic);
       }
       return markup;
   }
}
