package br.com.seutempo.api.controller.exception

import br.com.seutempo.api.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.exception.users.request.ErrorMessageModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandlerController {
    @ExceptionHandler(UserAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleUserAlreadyExistsException(ex: UserAlreadyExistsException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                HttpStatus.CONFLICT.value(),
                ex.message,
            )
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }
}
