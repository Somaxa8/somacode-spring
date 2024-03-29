package com.somacode.somacode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(
        uniqueConstraints = [
                UniqueConstraint(columnNames = [User_.EMAIL])
        ]
)
class User(
        @Id @GeneratedValue
        var id: Long? = null,
        var email: String? = null,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        var password: String? = null,
        var name: String? = null,
        var lastname: String? = null,

        @JsonIgnore
        @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
        var authorities: MutableSet<Authority> = mutableSetOf()

): Serializable
