package com.somacode.somacode.repository.criteria

import com.somacode.somacode.entity.Blog
import com.somacode.somacode.entity.BlogCategory_
import com.somacode.somacode.entity.Blog_
import com.somacode.somacode.service.tool.CriteriaTool
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Path

@Repository
class BlogCriteria {

    @PersistenceContext lateinit var entityManager: EntityManager


    fun findFilterPageable(page: Int, size: Int, search: String?, categoryId: Long?): Page<Blog> {
        val cb = entityManager.criteriaBuilder
        val q = cb.createQuery(Blog::class.java)
        val blog = q.from(Blog::class.java)

        val b: Path<Set<String>> = blog.get(Blog_.CATEGORY)
        val category: Path<Set<String>> = b.get(BlogCategory_.ID)

        categoryId?.let {
            q.select(blog).where(cb.equal(category, categoryId))
        } ?: run {
            q.select(blog).where()
        }

        return CriteriaTool.page(entityManager, q, page, size)
    }
}