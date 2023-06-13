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

package org.wso2.carbon.identity.oauth.par.core;

import org.wso2.carbon.identity.oauth.par.exceptions.ParCoreException;
import org.wso2.carbon.identity.oauth.par.model.ParAuthResponseData;

import java.util.Map;


/**
 * Provides authentication services.
 */
public interface ParAuthService {

    /**
     * Creates PAR AuthenticationResponse.
     *
     * @return parAuthResponse that contains response data for request.
     */
    ParAuthResponseData generateParAuthResponse(Map<String, String> parameters) throws ParCoreException;

    /**
     * Retrieve parameters from store.
     *
     * @return parameter map for request.
     */
    Map<String, String> retrieveParams(String uuid, String clientId) throws ParCoreException;
}
