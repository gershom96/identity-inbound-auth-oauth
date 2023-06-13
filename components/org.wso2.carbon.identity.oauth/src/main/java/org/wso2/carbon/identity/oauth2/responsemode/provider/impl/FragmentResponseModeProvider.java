/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.oauth2.responsemode.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.application.authentication.framework.util.FrameworkUtils;
import org.wso2.carbon.identity.oauth.common.OAuthConstants;
import org.wso2.carbon.identity.oauth2.responsemode.provider.AbstractResponseModeProvider;
import org.wso2.carbon.identity.oauth2.responsemode.provider.AuthorizationResponseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used when response_mode = fragment.
 */
public class FragmentResponseModeProvider extends AbstractResponseModeProvider {

    private static final String RESPONSE_MODE = OAuthConstants.ResponseModes.FRAGMENT;

    @Override
    public String getResponseMode() {

        return RESPONSE_MODE;
    }

    @Override
    public String getAuthResponseRedirectUrl(AuthorizationResponseDTO authorizationResponseDTO) {

        String redirectUrl = authorizationResponseDTO.getRedirectUrl();
        String sessionState = authorizationResponseDTO.getSessionState();
        String state = authorizationResponseDTO.getState();

        if (!authorizationResponseDTO.isError()) {
            String code = authorizationResponseDTO.getSuccessResponseDTO().getAuthorizationCode();
            String idToken = authorizationResponseDTO.getSuccessResponseDTO().getIdToken();
            String accessToken = authorizationResponseDTO.getSuccessResponseDTO().getAccessToken();
            String tokenType = authorizationResponseDTO.getSuccessResponseDTO().getTokenType();
            long validityPeriod = authorizationResponseDTO.getSuccessResponseDTO().getValidityPeriod();
            String scope = authorizationResponseDTO.getSuccessResponseDTO().getScope();
            String authenticatedIdPs = authorizationResponseDTO.getAuthenticatedIDPs();
            List<String> queryParams = new ArrayList<>();
            if (accessToken != null) {
                queryParams.add(OAuthConstants.ACCESS_TOKEN_RESPONSE_PARAM + "=" + accessToken);
                queryParams.add(OAuthConstants.EXPIRES_IN + "=" + validityPeriod);
            }

            if (tokenType != null) {
                queryParams.add(OAuthConstants.TOKEN_TYPE + "=" + tokenType);
            }

            if (idToken != null) {
                queryParams.add(OAuthConstants.ID_TOKEN + "=" + idToken);
            }

            if (code != null) {
                queryParams.add(OAuthConstants.CODE + "=" + code);
            }

            if (authenticatedIdPs != null && !authenticatedIdPs.isEmpty()) {
                queryParams.add(OAuthConstants.AUTHENTICATED_IDPS + "=" + authenticatedIdPs);
            }

            if (sessionState != null) {
                queryParams.add(OAuthConstants.SESSION_STATE + "=" + sessionState);
            }

            if (state != null) {
                queryParams.add(OAuthConstants.STATE + "=" + state);
            }

            if (scope != null) {
                queryParams.add(OAuthConstants.SCOPE + "=" + scope);
            }

            redirectUrl = FrameworkUtils.appendQueryParamsStringToUrl(redirectUrl,
                    String.join("&", queryParams));

            redirectUrl = redirectUrl.replace("?", "#");

        } else {
            redirectUrl += "#" +
                    OAuthConstants.OAUTH_ERROR + "=" + authorizationResponseDTO.getErrorResponseDTO().getError() +
                    "&" + OAuthConstants.OAUTH_ERROR_DESCRIPTION + "=" +
                    authorizationResponseDTO.getErrorResponseDTO().getErrorDescription()
                            .replace(" ", "+");

            if (StringUtils.isNotBlank(authorizationResponseDTO.getSessionState())) {
                redirectUrl += "&" + OAuthConstants.SESSION_STATE + "=" +
                        authorizationResponseDTO.getSessionState();
            }

            if (StringUtils.isNotBlank(state)) {
                redirectUrl += "&" + OAuthConstants.STATE + "=" + state;
            }
        }
        authorizationResponseDTO.setRedirectUrl(redirectUrl);
        return redirectUrl;
    }

    @Override
    public String getAuthResponseBuilderEntity(AuthorizationResponseDTO authorizationResponseDTO) {

        return null;
    }

    @Override
    public AuthResponseType getAuthResponseType() {

        return AuthResponseType.REDIRECTION;
    }
}
