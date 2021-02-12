package com.somacode.somacode.service

import com.somacode.somacode.config.exception.BadRequestException
import com.somacode.somacode.entity.BlogCategory
import com.somacode.somacode.repository.BlogCategoryRepository
import com.somacode.somacode.config.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class BlogCategoryService {

    @Autowired lateinit var blogCategoryRepository: BlogCategoryRepository


    fun init() {
        if (blogCategoryRepository.count() <= 0) {
            create("Java", null)
            create("Spring Boot", 1)
        }
    }

    fun create(title: String, parentId: Long?): BlogCategory {
        val blogCategory = BlogCategory(title = title)

        parentId?.let { blogCategory.parent = findById(it) }

        return blogCategoryRepository.save(blogCategory)
    }

    fun update(id: Long, title: String?, parentId: Long?): BlogCategory {
        if (parentId != null) {
            if (!blogCategoryRepository.existsById(parentId)) {
                throw NotFoundException("parentId does not exist")
            } else if (parentId == id) {
                throw BadRequestException("productCategoryId cannot be its parent")
            }
        }

        val blogCategory = findById(id)

        parentId?.let {
            blogCategory.parent = findById(it)
        } ?: run {
            blogCategory.parent = null
        }

        title?.let { blogCategory.title = it }

        return blogCategoryRepository.save(blogCategory)
    }

    fun findById(id: Long): BlogCategory {
        if (!blogCategoryRepository.existsById(id)) {
            throw NotFoundException()
        }
        val blogCategory = blogCategoryRepository.getOne(id)
        blogCategory.hasChildren = blogCategory.children.isNotEmpty()
        return blogCategory
    }

    fun delete(id: Long) {
        if (!blogCategoryRepository.existsById(id)) {
            throw NotFoundException()
        }
        blogCategoryRepository.deleteById(id)
    }

    fun findAll(): List<BlogCategory> {
        return blogCategoryRepository.findAll()
    }
}