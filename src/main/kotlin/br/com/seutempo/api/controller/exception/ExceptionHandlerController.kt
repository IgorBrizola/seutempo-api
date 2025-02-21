package br.com.seutempo.api.controller.exception

import br.com.seutempo.api.model.exception.BusinessException
import br.com.seutempo.api.model.exception.ErrorMessageModel
import br.com.seutempo.api.model.exception.ResourceAlreadyExistsException
import br.com.seutempo.api.model.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandlerController {
    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                HttpStatus.NOT_FOUND.value(),
                ex.message,
            )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleResourceAlreadyExistsException(ex: ResourceAlreadyExistsException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                HttpStatus.CONFLICT.value(),
                ex.message,
            )
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                HttpStatus.BAD_REQUEST.value(),
                ex.message,
            )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}
