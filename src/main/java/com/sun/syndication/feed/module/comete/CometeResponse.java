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

package com.sun.syndication.feed.module.comete;

import java.util.List;

import com.sun.syndication.feed.atom.Link;

/** Provides access to A9 Open Search information.
 * @author Frederic Bergeron (frederic.bergeron@licef.ca)
 */
public interface CometeResponse{

    public void setExtraInfos( List extraInfos );
    public List getExtraInfos(); 
    public void addExtraInfo( String extraInfo );

}
