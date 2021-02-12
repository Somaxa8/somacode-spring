package com.somacode.somacode.service

import com.somacode.somacode.config.exception.NotFoundException
import com.somacode.somacode.entity.Config
import com.somacode.somacode.repository.ConfigRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ConfigService {

    @Autowired lateinit var configRepository: ConfigRepository


    fun init() {
        if (configRepository.count() <= 0) {
            TODO("IMPLEMENT THIS")
        }
    }

    fun create(config: Config): Config {
        config.id = null
        if (config.location == null) config.location = 1000
        return configRepository.save(config)
    }

    fun update(id: Long, request: Config): Config {
        val config = findById(id)

        request.key?.let { config.key = it }
        request.value?.let { config.value = it }
        request.url?.let { config.url = it }
        request.meta?.let { config.meta = it }
        request.tag?.let { config.tag = it }
        request.location?.let {
            config.location = it
        } ?: run {
            config.location = 1000
        }

        return configRepository.save(config)
    }

    fun findById(id: Long): Config {
        if (!configRepository.existsById(id)) {
            throw NotFoundException()
        }
        return configRepository.getOne(id)
    }

    fun findAllByKey(key: String): List<Config> {
        return configRepository.findByKeyOrderByLocationAsc(key)
    }

    fun delete(id: Long) {
        if (!configRepository.existsById(id)) {
            throw NotFoundException()
        }
        configRepository.deleteById(id)
    }

}