package com.somacode.somacode.config

import com.somacode.somacode.entity.Authority
import com.somacode.somacode.entity.oauth.OAuthClientDetails
import com.somacode.somacode.repository.OAuthClientDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class DatabaseConfig {

    @Autowired lateinit var oAuthClientDetailsRepository: OAuthClientDetailsRepository
    @Autowired lateinit var passwordEncoder: PasswordEncoder
    @Autowired lateinit var populateConfig: PopulateConfig
//    @Autowired lateinit var profileService: ProfileTool
//    @Autowired lateinit var logService: LogService
    @Value("\${custom.my-secret-token}") lateinit var mySecretToken: String
    @Value("\${custom.access-token-validity}") var accessTokenValidity: Int? = null
    @Value("\${custom.refresh-token-validity}") var refreshTokenValidity: Int? = null
    @Value("\${spring.application.name}") lateinit var applicationName: String


    @PostConstruct
    fun init() {
        if (!oAuthClientDetailsRepository.findById(applicationName).isPresent) {
            val oAuthClientDetails = OAuthClientDetails()
            oAuthClientDetails.clientId = applicationName
            oAuthClientDetails.resourceId = "res_$applicationName"
            oAuthClientDetails.clientSecret = passwordEncoder.encode(mySecretToken)
            oAuthClientDetails.scope = "read,write"
            oAuthClientDetails.authorizedGrantTypes = "password,refresh_token,authorization_code,implicit"
            val authorities = StringJoiner(",")
            for (authority in Authority.Role.values()) {
                authorities.add(authority.toString())
            }
//            LogService.out.info("Authorities: $authorities")
            oAuthClientDetails.authorities = authorities.toString()
            oAuthClientDetails.accessTokenValidity = accessTokenValidity
            oAuthClientDetails.refreshTokenValidity = refreshTokenValidity
            oAuthClientDetails.additionalInformation = "{}"
            oAuthClientDetails.autoapprove = "true"
            oAuthClientDetailsRepository.save(oAuthClientDetails)
        }

        populateConfig.init()

    }

}