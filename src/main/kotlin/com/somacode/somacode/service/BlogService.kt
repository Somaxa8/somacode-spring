package com.somacode.somacode.service

import com.somacode.somacode.entity.Blog
import com.somacode.somacode.repository.BlogRepository
import com.somacode.somacode.config.exception.NotFoundException
import com.somacode.somacode.entity.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.transaction.Transactional

@Service
@Transactional
class BlogService {

    @Autowired lateinit var blogRepository: BlogRepository
    @Autowired lateinit var documentService: DocumentService
    @Autowired lateinit var blogCategoryService: BlogCategoryService


    fun init() {
        if (blogRepository.count() <= 0) {
            TODO("IMPLEMENT THIS")
        }
    }

    fun create(title: String, description: String, body: String, categoryId: Long, bannerFile: MultipartFile): Blog {
        val category = blogCategoryService.findById(categoryId)
        val blog = Blog(
                title = title,
                description = description,
                body = body,
                category = category
        )

        blog.banner = documentService.create(bannerFile, Document.Type.IMAGE, Blog::class.java.simpleName, null)

        return blogRepository.save(blog)
    }

    fun update(id: Long, title: String?, description: String?, body: String, categoryId: Long?, bannerFile: MultipartFile?): Blog {
        val blog = findById(id)
        title?.let {
            if (it.isBlank()) throw IllegalArgumentException()
            blog.title = it
        }
        body?.let {
            if (it.isBlank()) throw IllegalArgumentException()
            blog.body = it
        }
        categoryId?.let {
            val category = blogCategoryService.findById(it)
            blog.category = category
        }

        if (bannerFile != null) {
            val oldBanner = blog.banner
            blog.banner = documentService.create(bannerFile, Document.Type.IMAGE, Blog::class.java.simpleName, null)
            oldBanner?.let { documentService.delete(it.id!!) }
        }

        return blogRepository.save(blog)
    }

    fun findById(id: Long): Blog {
        if (!blogRepository.existsById(id)) {
            throw NotFoundException()
        }
        return blogRepository.getOne(id)
    }

    fun delete(id: Long) {
        if (!blogRepository.existsById(id)) {
            throw NotFoundException()
        }
        blogRepository.deleteById(id)
    }

    fun findAll(): List<Blog> {
        return blogRepository.findAll()
    }
}