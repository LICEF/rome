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

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.ModuleImpl;
import com.rometools.rome.feed.module.comete.CometeModule;
import com.rometools.rome.feed.module.comete.impl.util.LangString;
import com.rometools.rome.feed.module.comete.impl.util.LangStringImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Frederic Bergeron (frederic.bergeron@licef.ca)
 * Comete Module implementation
 */
public class CometeModuleImpl extends ModuleImpl implements CometeModule, Serializable {

    public CometeModuleImpl() {
        super(CometeModuleImpl.class, CometeModuleImpl.URI);
    }

    public List<LangString> getExtraInfos() {
        this.extraInfos = (extraInfos == null) ? new LinkedList<LangString>() : extraInfos;

        return this.extraInfos;
    }

    public void setExtraInfos( List<LangString> extraInfos ) {
        this.extraInfos = extraInfos;
    }

    public void addExtraInfo( String string ) {
        LangString langString = new LangStringImpl();
        langString.setString( string );
        addExtraInfo( langString );
    }

    public void addExtraInfo( LangString langString ) {
        if( extraInfos == null )
            extraInfos = new LinkedList<LangString>();
        extraInfos.add( langString );
    }

    public List<LangString> getKeywords() {
        this.keywords = (keywords == null) ? new LinkedList<LangString>() : keywords;

        return this.keywords;
    }

    public void setKeywords( List<LangString> keywords ) {
        this.keywords = keywords;
    }

    public void addKeyword( String string ) {
        LangString langString = new LangStringImpl();
        langString.setString( string );
        addKeyword( langString );
    }

    public void addKeyword( LangString langString ) {
        if( keywords == null )
            keywords = new LinkedList<LangString>();
        keywords.add( langString );
    }

    public void setAdded( Date date ) {
        added = date;
    }

    public Date getAdded() {
        return( added );
    }

    public void setUpdated( Date date ) {
        updated = date;
    }

    public Date getUpdated() {
        return( updated );
    }

    public List<String> getFlags() {
        this.flags = (flags == null) ? new LinkedList<String>() : flags;

        return this.flags;
    }

    public void setFlags( List<String> flags ) {
        this.flags = flags;
    }

    public void addFlag( String flag ) {
        if( flags == null )
            flags = new LinkedList<String>();
        flags.add( flag );
    }

    /* (non-Javadoc)
     * @see com.rometools.rome.feed.CopyFrom#copyFrom(java.lang.Object)
     */
    public void copyFrom(CopyFrom obj) {
        CometeModule cm = (CometeModuleImpl) obj;

        setExtraInfos( cm.getExtraInfos() );
        setKeywords( cm.getKeywords() );
        setFlags( cm.getFlags() );
    }

    /* (non-Javadoc)
     * @see com.rometools.rome.feed.CopyFrom#getInterface()
     */
    public Class getInterface() {
        // TODO Auto-generated method stub
        return CometeModule.class;
    }

    private List<LangString> extraInfos;    
    private List<LangString> keywords;    
    private Date added;
    private Date updated;
    private List<String> flags;

}
