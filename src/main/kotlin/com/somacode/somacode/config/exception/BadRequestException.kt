package com.somacode.somacode.config.exception

class BadRequestException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("BadRequest")
}