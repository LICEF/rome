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

import com.rometools.rome.feed.module.Module;

/** Provides access to Comete information.
 * @author Frederic Bergeron (frederic.bergeron@licef.ca)
 */
public interface CometeModule extends Module, CometeResponse {

    public final static String URI = "http://comete.licef.ca/reference#";
    
}
