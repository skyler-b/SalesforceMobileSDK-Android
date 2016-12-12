/*
 * Copyright (c) 2016-present, salesforce.com, inc.
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
package com.salesforce.androidsdk.store;

import com.salesforce.androidsdk.smartstore.store.SoupSpec;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Tests for SoupSpec
 */
public class SoupSpecTest extends TestCase {
    private static final String TEST_SOUP_NAME = "testSoupName";
    private static final String TEST_FEATURE_1 = "testFeature1";
    private static final String TEST_FEATURE_2 = "testFeature2";

    public void testSoupSpecNameOnly() {

        SoupSpec soupSpec = new SoupSpec(TEST_SOUP_NAME);

        assertEquals("SoupSpec does not have given soup name", TEST_SOUP_NAME, soupSpec.getSoupName());
        assertTrue("SoupSpec must not have any features", soupSpec.getFeatures().isEmpty());
    }

    public void testSoupSpecFeatures() {
        SoupSpec soupSpec = new SoupSpec(TEST_SOUP_NAME, TEST_FEATURE_1, TEST_FEATURE_2);

        assertEquals("SoupSpec does not have given soup name", TEST_SOUP_NAME, soupSpec.getSoupName());
        assertTrue("SoupSpec does not have given feature", soupSpec.getFeatures().contains(TEST_FEATURE_1));
        assertTrue("SoupSpec does not have given feature", soupSpec.getFeatures().contains(TEST_FEATURE_2));

        soupSpec = new SoupSpec(TEST_SOUP_NAME, (String[]) null);
        assertTrue("SoupSpec must not have any features", soupSpec.getFeatures().isEmpty());
    }

    public void testToJSON() throws JSONException {
        SoupSpec soupSpec = new SoupSpec(TEST_SOUP_NAME, TEST_FEATURE_1, TEST_FEATURE_2);

        JSONObject result = soupSpec.toJSON();

        assertEquals("Soup name in json representation is incorrect.", TEST_SOUP_NAME, result.getString("name"));
        assertEquals("Feature 1 in json representation is incorrect.", TEST_FEATURE_1, result.getJSONArray("features").get(0));
        assertEquals("Feature 1 in json representation is incorrect.", TEST_FEATURE_2, result.getJSONArray("features").get(1));
    }
}
