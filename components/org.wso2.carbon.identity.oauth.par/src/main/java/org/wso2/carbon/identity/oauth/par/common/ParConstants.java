/**
 * Copyright (c) 2023, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.oauth.par.common;

/**
 * Contains the required constants for PAR feature.
 */
public class ParConstants {

    public static final long EXPIRES_IN_DEFAULT_VALUE_IN_SEC = 60;
    public static final long SEC_TO_MILLISEC_FACTOR = 1000;
    public static final String UTC = "UTC";
    public static final String EXPIRES_IN = "expires_in";
    public static final String REQUEST_URI_HEAD = "urn:ietf:params:wso2is:request_uri:";
    public static final String REQUEST_URI_IN_REQUEST_BODY_ERROR = "request.with.request_uri.not.allowed";
    public static final String CACHE_NAME = "ParCache";
    public static final String COL_LBL_JSON_PARAMS = "PARAMETERS";
    public static final String COL_LBL_SCHEDULED_EXPIRY = "SCHEDULED_EXPIRY";
    public static final String COL_LBL_CLIENT_ID = "CLIENT_ID";


    private ParConstants() {

    }
}
