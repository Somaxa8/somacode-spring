package com.somacode.somacode.config.exception

class ForbiddenException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("Forbidden")
}