package com.somacode.somacode.repository

import com.somacode.somacode.entity.BlogCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogCategoryRepository : JpaRepository<BlogCategory, Long> {
}