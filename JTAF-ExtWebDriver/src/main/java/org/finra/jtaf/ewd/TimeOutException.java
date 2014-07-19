/*
 * (C) Copyright 2013 Java Test Automation Framework Contributors.
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

package org.finra.jtaf.ewd;

import com.thoughtworks.selenium.SeleniumException;

/**
 * Thrown to indicate that an operation has timed out.
 * 
 */
public class TimeOutException extends SeleniumException {

    private static final long serialVersionUID = -545182997528926254L;

    /**
     * Constructs a {@code TimeOutException} with the specified detail message.
     * 
     * @param e
     *            the detail message
     */
    public TimeOutException(String e) {
        super(e);
    }

}
