package com.somacode.somacode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class BlogCategory(
        @Id @GeneratedValue
        var id: Long? = null,
        var title: String? = null,
        @JsonIgnore
        @OneToMany(mappedBy = "category")
        var blogs: List<Blog> = listOf(),
        @ManyToOne
        var parent: BlogCategory? = null,
        @JsonIgnore
        @OneToMany(mappedBy = "parent")
        var children: List<BlogCategory> = listOf(),
        @Transient
        var hasChildren: Boolean? = null
)