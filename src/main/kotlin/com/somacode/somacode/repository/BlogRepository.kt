package com.somacode.somacode.repository

import com.somacode.somacode.entity.Blog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRepository : JpaRepository<Blog, Long> {
}