package com.somacode.somacode.entity.oauth

import java.sql.Blob
import javax.persistence.*

@Entity
@Table(name = "oauth_refresh_token")
class OAuthRefreshToken(
        @Id @GeneratedValue
        var id: Long? = null,
        @Column(name = "token_id")
        var tokenId: String? = null,
        @Lob
        var token: Blob? = null,
        @Lob
        var authentication: Blob? = null
)