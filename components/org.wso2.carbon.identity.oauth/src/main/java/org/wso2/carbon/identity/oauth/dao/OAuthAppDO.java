/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.identity.oauth.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.wso2.carbon.identity.application.authentication.framework.model.AuthenticatedUser;
import org.wso2.carbon.identity.application.common.model.InboundConfigurationProtocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * OAuth application data object.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "oAuthAppDO")
@JsonTypeName("oAuthAppDO")
public class OAuthAppDO extends InboundConfigurationProtocol implements Serializable {

    private static final long serialVersionUID = -6453843721358989519L;

    @XmlTransient
    private int id;
    private String oauthConsumerKey;
    private String oauthConsumerSecret;
    private String applicationName;
    private String callbackUrl;
    private String oauthVersion;
    private String grantTypes;
    @XmlElementWrapper(name = "scopeValidators")
    @XmlElement(name = "scopeValidator")
    private String[] scopeValidators;
    private boolean pkceSupportPlain;
    private boolean pkceMandatory;
    private String state;
    private long userAccessTokenExpiryTime;
    private long applicationAccessTokenExpiryTime;
    private long refreshTokenExpiryTime;
    private long idTokenExpiryTime;
    @XmlElementWrapper(name = "audiences")
    @XmlElement(name = "audience")
    private String[] audiences = new String[0];
    @XmlElementWrapper(name = "idTokenAudiences")
    @XmlElement(name = "idTokenAudience")
    private String[] idTokenAudiences = new String[0];
    @XmlElementWrapper(name = "accessTokenAudiences")
    @XmlElement(name = "accessTokenAudience")
    private String[] accessTokenAudiences = new String[0];
    private boolean bypassClientCredentials;
    private String renewRefreshTokenEnabled;
    // OIDC related properties.
    private boolean requestObjectSignatureValidationEnabled;
    private boolean idTokenEncryptionEnabled;
    private String idTokenEncryptionAlgorithm;
    private String idTokenEncryptionMethod;
    private String backChannelLogoutUrl;
    private String frontchannelLogoutUrl;
    @XmlTransient
    @JsonIgnore
    private AuthenticatedUser appOwner;
    private String tokenType;
    private String tokenBindingType;
    private boolean tokenRevocationWithIDPSessionTerminationEnabled;
    private boolean tokenBindingValidationEnabled;

    public AuthenticatedUser getAppOwner() {

        return appOwner;
    }
    public void setAppOwner(AuthenticatedUser appOwner) {

        this.appOwner = appOwner;
    }

    /**
     * @deprecated use {@link #getAppOwner()} instead.
     */
    @Deprecated
    @JsonIgnore
    public AuthenticatedUser getUser() {
        return this.getAppOwner();
    }

    /**
     * @deprecated use {@link #setAppOwner(AuthenticatedUser)} instead.
     */
    @Deprecated
    @JsonIgnore
    public void setUser(AuthenticatedUser user) {
        this.setAppOwner(user);
    }

    public String getOauthConsumerKey() {
        return oauthConsumerKey;
    }

    public void setOauthConsumerKey(String oauthConsumerKey) {
        this.oauthConsumerKey = oauthConsumerKey;
    }

    public String getOauthConsumerSecret() {
        return oauthConsumerSecret;
    }

    public void setOauthConsumerSecret(String oauthConsumerSecret) {
        this.oauthConsumerSecret = oauthConsumerSecret;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getOauthVersion() {
        return oauthVersion;
    }

    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String[] getScopeValidators() {
        return scopeValidators;
    }

    public void setScopeValidators(String[] scopeValidators) {
        this.scopeValidators = scopeValidators;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPkceSupportPlain() {
        return pkceSupportPlain;
    }

    public void setPkceSupportPlain(boolean pkceSupportPlain) {
        this.pkceSupportPlain = pkceSupportPlain;
    }

    public boolean isPkceMandatory() {
        return pkceMandatory;
    }

    public void setPkceMandatory(boolean pkceMandatory) {
        this.pkceMandatory = pkceMandatory;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public long getUserAccessTokenExpiryTime() {
        return userAccessTokenExpiryTime;
    }

    public void setUserAccessTokenExpiryTime(long userAccessTokenExpiryTime) {
        this.userAccessTokenExpiryTime = userAccessTokenExpiryTime;
    }

    public long getApplicationAccessTokenExpiryTime() {
        return applicationAccessTokenExpiryTime;
    }

    public void setApplicationAccessTokenExpiryTime(long applicationAccessTokenExpiryTime) {
        this.applicationAccessTokenExpiryTime = applicationAccessTokenExpiryTime;
    }

    public long getRefreshTokenExpiryTime() {
        return refreshTokenExpiryTime;
    }

    public void setRefreshTokenExpiryTime(long refreshTokenExpiryTime) {
        this.refreshTokenExpiryTime = refreshTokenExpiryTime;
    }

    /**
     * @deprecated use {@link #getIdTokenAudiences()} | {@link #getAccessTokenAudiences()} instead.
     */
    @Deprecated
    public String[] getAudiences() {
        return audiences;
    }

    /**
     * @deprecated use {@link #setIdTokenAudiences(String[])} instead.
     */
    @Deprecated
    public void setAudiences(String[] audiences) {
        this.audiences = audiences;
    }

    public String[] getIdTokenAudiences() {

        return idTokenAudiences;
    }

    public void setIdTokenAudiences(String[] idTokenAudiences) {

        this.idTokenAudiences = idTokenAudiences;
    }

    public String[] getAccessTokenAudiences() {

        return accessTokenAudiences;
    }

    public void setAccessTokenAudiences(String[] accessTokenAudiences) {

        this.accessTokenAudiences = accessTokenAudiences;
    }

    public boolean isRequestObjectSignatureValidationEnabled() {
        return requestObjectSignatureValidationEnabled;
    }

    public void setRequestObjectSignatureValidationEnabled(boolean requestObjectSignatureValidationEnabled) {
        this.requestObjectSignatureValidationEnabled = requestObjectSignatureValidationEnabled;
    }

    public boolean isIdTokenEncryptionEnabled() {
        return idTokenEncryptionEnabled;
    }

    public void setIdTokenEncryptionEnabled(boolean idTokenEncryptionEnabled) {
        this.idTokenEncryptionEnabled = idTokenEncryptionEnabled;
    }

    public String getIdTokenEncryptionAlgorithm() {
        return idTokenEncryptionAlgorithm;
    }

    public void setIdTokenEncryptionAlgorithm(String idTokenEncryptionAlgorithm) {
        this.idTokenEncryptionAlgorithm = idTokenEncryptionAlgorithm;
    }

    public String getIdTokenEncryptionMethod() {
        return idTokenEncryptionMethod;
    }

    public void setIdTokenEncryptionMethod(String idTokenEncryptionMethod) {
        this.idTokenEncryptionMethod = idTokenEncryptionMethod;
    }

    public void setBackChannelLogoutUrl(String backChannelLogoutUrl) {

        this.backChannelLogoutUrl = backChannelLogoutUrl;
    }

    public String getBackChannelLogoutUrl() {

        return backChannelLogoutUrl;
    }

    public String getFrontchannelLogoutUrl() {
        return frontchannelLogoutUrl;
    }

    public void setFrontchannelLogoutUrl(String frontchannelLogoutUrl) {
        this.frontchannelLogoutUrl = frontchannelLogoutUrl;
    }

    public long getIdTokenExpiryTime() {

        return idTokenExpiryTime;
    }

    public void setIdTokenExpiryTime(long idTokenExpiryTime) {
        this.idTokenExpiryTime = idTokenExpiryTime;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isBypassClientCredentials() {
        return bypassClientCredentials;
    }

    public void setBypassClientCredentials(boolean isPublicClient) {
        this.bypassClientCredentials = isPublicClient;
    }

    public void setRenewRefreshTokenEnabled(String renewRefreshTokenEnabled) {

        this.renewRefreshTokenEnabled = renewRefreshTokenEnabled;
    }

    public String getRenewRefreshTokenEnabled() {

        return renewRefreshTokenEnabled;
    }

    public String getTokenBindingType() {

        return tokenBindingType;
    }

    public void setTokenBindingType(String tokenBindingType) {

        this.tokenBindingType = tokenBindingType;
    }

    public boolean isTokenRevocationWithIDPSessionTerminationEnabled() {

        return tokenRevocationWithIDPSessionTerminationEnabled;
    }

    public void setTokenRevocationWithIDPSessionTerminationEnabled(
            boolean tokenRevocationWithIDPSessionTerminationEnabled) {

        this.tokenRevocationWithIDPSessionTerminationEnabled = tokenRevocationWithIDPSessionTerminationEnabled;
    }

    public boolean isTokenBindingValidationEnabled() {

        return tokenBindingValidationEnabled;
    }

    public void setTokenBindingValidationEnabled(boolean tokenBindingValidationEnabled) {

        this.tokenBindingValidationEnabled = tokenBindingValidationEnabled;
    }
}
