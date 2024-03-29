package com.somacode.somacode.entity.oauth

import java.sql.Blob
import javax.persistence.*

@Entity
@Table(name = "oauth_code")
class OAuthCode(
        @Id @GeneratedValue
        var id: Long? = null,
        var code: String? = null,
        @Lob
        var authentication: Blob? = null
)