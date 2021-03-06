/*
 * Copyright (c) 2014-present, salesforce.com, inc.
 * All rights reserved.
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 * - Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * - Neither the name of salesforce.com, inc. nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission of salesforce.com, inc.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.salesforce.androidsdk.smartsync.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract super class for SyncUpTarget and SyncDownTarget
 */
public abstract class SyncTarget {

    public static final String ANDROID_IMPL = "androidImpl";
    public static final String ID_FIELD_NAME = "idFieldName";
    public static final String MODIFICATION_DATE_FIELD_NAME = "modificationDateFieldName";

    private String idFieldName;
    private String modificationDateFieldName;

    public SyncTarget() {
        idFieldName = Constants.ID;
        modificationDateFieldName = Constants.LAST_MODIFIED_DATE;
    }

    public SyncTarget(JSONObject target) throws JSONException {
        idFieldName = target != null && target.has(ID_FIELD_NAME) ? target.getString(ID_FIELD_NAME) : Constants.ID;
        modificationDateFieldName = target != null && target.has(MODIFICATION_DATE_FIELD_NAME) ? target.getString(MODIFICATION_DATE_FIELD_NAME) : Constants.LAST_MODIFIED_DATE;
    }

    /**
     * @return json representation of target
     * @throws JSONException
     */
    public JSONObject asJSON() throws JSONException {
        JSONObject target = new JSONObject();
        target.put(ANDROID_IMPL, getClass().getName());
        target.put(ID_FIELD_NAME, idFieldName);
        target.put(MODIFICATION_DATE_FIELD_NAME, modificationDateFieldName);
        return target;
    }

    /**
     * @return The field name of the ID field of the record.  Defaults to "Id".
     */
    public String getIdFieldName() {
        return idFieldName;
    }

    /**
     * @return The field name of the modification date field of the record.  Defaults to "LastModifiedDate".
     */
    public String getModificationDateFieldName() {
        return modificationDateFieldName;
    }
}