package com.somacode.somacode.entity

import javax.persistence.*

@Entity
class Blog(
        @Id @GeneratedValue
        var id: Long? = null,
        var title: String? = null,
        var description: String? = null,
        var body: String? = null,
        @OneToOne
        var banner: Document? = null,
        @ManyToOne
        var category: BlogCategory? = null
) : Auditing()