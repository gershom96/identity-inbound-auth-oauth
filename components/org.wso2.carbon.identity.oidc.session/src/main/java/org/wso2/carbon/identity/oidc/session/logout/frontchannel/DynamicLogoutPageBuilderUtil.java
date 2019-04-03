/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
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

package org.wso2.carbon.identity.oidc.session.logout.frontchannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth.common.exception.InvalidOAuthClientException;
import org.wso2.carbon.identity.oauth.dao.OAuthAppDO;
import org.wso2.carbon.identity.oauth2.IdentityOAuth2Exception;
import org.wso2.carbon.identity.oidc.session.OIDCSessionState;
import org.wso2.carbon.identity.oidc.session.util.OIDCSessionManagementUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Dynamic HTML Content builder interface for OIDC Frontchannel logout. The HTML page is
 * generated by String manipulation of a static template.
 */
public class DynamicLogoutPageBuilderUtil {

    private static final Log log = LogFactory.getLog(DynamicLogoutPageBuilderUtil.class);

    private static final String regex = "\\$\\{([^}]+)\\}";
    private static final Pattern pattern = Pattern.compile(regex);
    private static Matcher matcher;

    private static final String STATIC_CONTENT_TEMPLATE =
            "<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\"> \n" +
                    "<script>\n" +
                    "var count = 0;\n" +
                    "function onIFrameLoad() {\n" +
                    "count++;\n" +
                    "if(count === " + "${frontchannelLogoutsCount}" + "){\n" +
                    "redirect();\n" +
                    "}\n" +
                    "};\n" +
                    "function redirect(){\n" +
                    "window.location = \"" + "${redirectURL}" + "\";\n" +
                    "};\n" +
                    "</script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "${body}\n" +
                    "</body>\n" +
                    "</html>";

    private static final String IFRAME_TEMPLATE =
            "<iframe \n" +
                    "\tsrc=\"" + "${frontchannelLogoutURL}" + "\"" +
                    "\tonload=\"onIFrameLoad()\">\n" +
                    "</iframe>\n";

    private static final String REDIRECT_SCRIPT_TEMPLATE =
            "<script>\n" +
                    "\tredirect();\n" +
                    "\t</script>\n";

    private static final String REDIRECT_URL = "redirectURL";
    private static final String FRONTCHANNEL_LOGOUT_URL = "frontchannelLogoutURL";
    private static final String BODY = "body";
    private static final String FRONTCHANNEL_LOGOUT_URLS_COUNT = "frontchannelLogoutsCount";

    public static String buildPage(HttpServletRequest request) {

        String htmlPage = STATIC_CONTENT_TEMPLATE;
        StringBuilder body = new StringBuilder();

        List<String> frontchannelLogoutURLs = getFrontchannelLogoutURLs(request);

        if (!frontchannelLogoutURLs.isEmpty()) {
            htmlPage = replacePlaceholder(htmlPage, FRONTCHANNEL_LOGOUT_URLS_COUNT, String.valueOf(frontchannelLogoutURLs.size()));
            for (String frontchannelLogoutURL : frontchannelLogoutURLs) {
                String iframe = replacePlaceholder(IFRAME_TEMPLATE, FRONTCHANNEL_LOGOUT_URL, frontchannelLogoutURL);
                body.append(iframe);
            }
        } else {
            htmlPage = replacePlaceholder(htmlPage, FRONTCHANNEL_LOGOUT_URLS_COUNT, String.valueOf(0));
            body.append(REDIRECT_SCRIPT_TEMPLATE);
        }

        htmlPage = replacePlaceholder(htmlPage, BODY, body.toString());
        return htmlPage;
    }

    public static String setRedirectURL(String htmlPage, String redirectURL) {

        if (htmlPage != null) {
            htmlPage = replacePlaceholder(htmlPage, REDIRECT_URL, redirectURL);
        }
        return htmlPage;
    }

    private static List<String> getFrontchannelLogoutURLs(HttpServletRequest request) {

        List<String> frontchannelLogoutURLs = new ArrayList<>();
        OIDCSessionState sessionState = OIDCSessionManagementUtil.getSessionState(request);
        if (sessionState != null) {
            Set<String> sessionParticipants = OIDCSessionManagementUtil.getSessionParticipants(sessionState);
            if (!sessionParticipants.isEmpty()) {
                OAuthAppDO oAuthAppDO;

                for (String clientID : sessionParticipants) {
                    try {
                        oAuthAppDO = OIDCSessionManagementUtil.getOAuthAppDO(clientID);
                        String frontchannelLogoutURL = oAuthAppDO.getFrontchannelLogoutUrl();
                        if (frontchannelLogoutURL != null) {
                            if (!frontchannelLogoutURL.equalsIgnoreCase(("null"))) {
                                frontchannelLogoutURLs.add(frontchannelLogoutURL);
                            }
                        }
                    } catch (IdentityOAuth2Exception e) {
                        log.error("Error while getting Logout URL for client id: " + clientID, e);
                    } catch (InvalidOAuthClientException e) {
                        log.error("Client id " + clientID + "is invalid.", e);
                    }
                }
            }
        }
        return frontchannelLogoutURLs;
    }

    private static String replacePlaceholder(String text, String token, String replacement) {

        matcher = pattern.matcher(text);
        while (matcher.find()) {
            String placeholder = matcher.group();
            String placeholderText = matcher.group(1);

            if (token.equals(placeholderText)) {
                text = text.replaceFirst(Pattern.quote(placeholder), replacement);
                break;
            }
        }
        return text;
    }
}