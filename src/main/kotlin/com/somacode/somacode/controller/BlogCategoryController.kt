package com.somacode.somacode.controller

import com.somacode.somacode.entity.BlogCategory
import com.somacode.somacode.service.BlogCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BlogCategoryController {

    @Autowired lateinit var blogCategoryService: BlogCategoryService


    @PostMapping("/api/blogCategory")
    fun postBlogCategory(
            //@RequestParam title: String
    ): ResponseEntity<BlogCategory> {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogCategoryService.create())
    }

    @PatchMapping("/api/blogCategory/{id}")
    fun pathBlogCategory(
            //@PathVariable id: Long,
            //@RequestParam(required = false) title: String?,
    ): ResponseEntity<BlogCategory> {
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.update())
    }

    @DeleteMapping("/api/blogCategory/{id}")
    fun deleteBlogCategory(@PathVariable id: Long): ResponseEntity<Void> {
        blogCategoryService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    }

    @GetMapping("/public/blogCategory")
    fun getBlogCategorys(): ResponseEntity<List<BlogCategory>> {
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.findAll())
    }

    @GetMapping("/public/blogCategory/{id}")
    fun getBlogCategory(@PathVariable id: Long): ResponseEntity<BlogCategory> {
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.findById(id))
    }

}