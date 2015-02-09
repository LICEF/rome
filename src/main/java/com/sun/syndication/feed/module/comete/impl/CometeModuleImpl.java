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

import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.module.ModuleImpl;
import com.sun.syndication.feed.module.comete.CometeModule;

import java.io.Serializable;
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

    public List getExtraInfos() {
        this.extraInfos = (extraInfos == null) ? new LinkedList() : extraInfos;

        return this.extraInfos;
    }

    public void setExtraInfos( List extraInfos ) {
        this.extraInfos = extraInfos;
    }

    public void addExtraInfo( String extraInfo ) {
        if( extraInfos != null )
            extraInfos.add( extraInfo );
        else {
            extraInfos = new LinkedList();
            extraInfos.add( extraInfo );
        }
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.feed.CopyFrom#copyFrom(java.lang.Object)
     */
    public void copyFrom(Object obj) {
        CometeModule cm = (CometeModuleImpl) obj;

        setExtraInfos( cm.getExtraInfos() );
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.feed.CopyFrom#getInterface()
     */
    public Class getInterface() {
        // TODO Auto-generated method stub
        return CometeModule.class;
    }

    private List extraInfos;    

}
