package com.somacode.somacode.repository

import com.somacode.somacode.entity.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository: JpaRepository<Document, Long> {

}