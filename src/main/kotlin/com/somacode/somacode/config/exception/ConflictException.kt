package com.somacode.somacode.config.exception

class ConflictException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(): super("Conflict")
}