package br.com.seutempo.api.controller.exception

import br.com.seutempo.api.model.exception.ErrorMessageModel
import br.com.seutempo.api.model.exception.category.CategoryAlreadyExistsException
import br.com.seutempo.api.model.exception.specialty.SpecialtyAlreadyExistsException
import br.com.seutempo.api.model.exception.users.UserAlreadyExistsException
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

    @ExceptionHandler(CategoryAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleCategoryAlreadyExistsException(ex: CategoryAlreadyExistsException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                HttpStatus.CONFLICT.value(),
                ex.message,
            )
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(SpecialtyAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleSpecialtyAlreadyExistsException(ex: SpecialtyAlreadyExistsException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                HttpStatus.CONFLICT.value(),
                ex.message,
            )
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }
}
