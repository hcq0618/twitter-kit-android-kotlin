/*
 * Copyright (C) 2015 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.twitter.sdk.android.core.internal.oauth

import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterAuthToken

internal class OAuth1aHeaders {

    companion object {
        private const val HEADER_AUTH_SERVICE_PROVIDER = "X-Auth-Service-Provider"
        private const val HEADER_AUTH_CREDENTIALS = "X-Verify-Credentials-Authorization"
    }

    /**
     * Gets authorization header for inclusion in HTTP request headers.
     *
     * @param authConfig The auth config.
     * @param authToken The auth token to use to sign the request.
     * @param callback The callback url.
     * @param method The HTTP method.
     * @param url The url.
     * @param postParams The post parameters.
     */
    fun getAuthorizationHeader(
        authConfig: TwitterAuthConfig,
        authToken: TwitterAuthToken?,
        callback: String?,
        method: String,
        url: String,
        postParams: Map<String, String>?
    ): String {
        val oAuth1aParameters = getOAuth1aParameters(
            authConfig, authToken, callback, method, url, postParams
        )
        return oAuth1aParameters.authorizationHeader
    }

    /**
     * @param authConfig The auth config.
     * @param authToken  The auth token to use.
     * @param callback   The callback url.
     * @param method     The HTTP method (GET, POST, PUT, DELETE, etc).
     * @param url        The url delegation should be sent to (e.g. https://api.twitter.com/1.1/account/verify_credentials.json).
     * @param postParams The post parameters.
     * @return A map of OAuth Echo headers
     */
    fun getOAuthEchoHeaders(
        authConfig: TwitterAuthConfig,
        authToken: TwitterAuthToken,
        callback: String?,
        method: String,
        url: String,
        postParams: Map<String, String>?
    ): Map<String, String> {
        val authorizationHeader = getAuthorizationHeader(
            authConfig, authToken, callback, method, url, postParams
        )
        return mutableMapOf(
            HEADER_AUTH_CREDENTIALS to authorizationHeader,
            HEADER_AUTH_SERVICE_PROVIDER to url
        )
    }

    fun getOAuth1aParameters(
        authConfig: TwitterAuthConfig,
        authToken: TwitterAuthToken?,
        callback: String?,
        method: String,
        url: String,
        postParams: Map<String, String>?
    ): OAuth1aParameters {
        return OAuth1aParameters(authConfig, authToken, callback, method, url, postParams)
    }
}