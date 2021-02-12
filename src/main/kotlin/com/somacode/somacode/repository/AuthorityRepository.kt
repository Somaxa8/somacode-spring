package com.somacode.somacode.repository

import com.somacode.somacode.entity.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository: JpaRepository<Authority, Authority.Role> {

    fun findByUsers_Id(userId: Long): List<Authority>

//    fun findAllByOrderByName(): List<Authority>

}