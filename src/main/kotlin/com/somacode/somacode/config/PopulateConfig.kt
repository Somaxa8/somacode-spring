package com.somacode.somacode.config

import com.somacode.somacode.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PopulateConfig {

    @Autowired lateinit var authorityService: AuthorityService
    @Autowired lateinit var userService: UserService


    fun init() {
        authorityService.init()
        userService.init()
    }

}