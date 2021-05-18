package com.rossio.exhibitions.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.CONFLICT)
class IntroductionAlreadyPresent() : RuntimeException("This Exhibition Already Contains One Introduction")

@ResponseStatus(HttpStatus.BAD_REQUEST)
class WrongTypeException(message: String) : RuntimeException(message)