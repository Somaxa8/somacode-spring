package com.somacode.somacode.controller

import com.somacode.somacode.entity.Blog
import com.somacode.somacode.service.BlogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class BlogController {

    @Autowired lateinit var blogService: BlogService


    @PostMapping("/api/blog")
    fun postBlog(
            @RequestParam title: String,
            @RequestParam description: String,
            @RequestParam body: String,
            @RequestParam categoryId: Long,
            @RequestParam bannerFile: MultipartFile
    ): ResponseEntity<Blog> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                blogService.create(title, description, body, categoryId, bannerFile)
        )
    }

    @PatchMapping("/api/blog/{id}")
    fun pathBlog(
            @PathVariable id: Long,
            @RequestParam(required = false) title: String?,
            @RequestParam(required = false) description: String?,
            @RequestParam(required = false) body: String?,
            @RequestParam(required = false) categoryId: Long?,
            @RequestParam(required = false) bannerFile: MultipartFile?
    ): ResponseEntity<Blog> {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.update(id, title, description, body, categoryId, bannerFile))
    }

    @DeleteMapping("/api/blog/{id}")
    fun deleteBlog(@PathVariable id: Long): ResponseEntity<Void> {
        blogService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    }

    @GetMapping("/public/blog")
    fun getBlogs(): ResponseEntity<List<Blog>> {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.findAll())
    }

    @GetMapping("/public/blog/{id}")
    fun getBlog(@PathVariable id: Long): ResponseEntity<Blog> {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.findById(id))
    }

}