package com.somacode.somacode.entity.model

import com.somacode.somacode.entity.Authority
import com.somacode.somacode.entity.User
import org.springframework.security.oauth2.common.OAuth2AccessToken

data class LoginResponse(
        var oAuth2AccessToken: OAuth2AccessToken,
        var user: User,
        var authorities: Set<Authority>
)