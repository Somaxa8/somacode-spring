package com.somacode.somacode.repository

import com.somacode.somacode.entity.oauth.OAuthClientDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OAuthClientDetailsRepository: JpaRepository<OAuthClientDetails, String> {

}