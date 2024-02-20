package me.dio.creditapplicationsystem.exceptions

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun validExceptionHandler(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
    val errors: MutableMap<String, String?> = HashMap()
    ex.bindingResult.allErrors.stream().forEach { error: ObjectError ->
      val fieldName: String = (error as FieldError).field
      val messageError: String? = (error.defaultMessage)
      errors[fieldName] = messageError
    }
    return ResponseEntity(
        ExceptionDetails(
            title = "Bad Request | Consult the documentation ",
            status = HttpStatus.BAD_REQUEST.value(),
            details = errors,
            exception = ex.javaClass.toString(),
            timestamp = LocalDateTime.now()
        ), HttpStatus.BAD_REQUEST
    )
  }

  @ExceptionHandler(DataAccessException::class)
  fun validExceptionHandler(ex: DataAccessException): ResponseEntity<ExceptionDetails> {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionDetails(
        title = "Conflict | Consult the documentation ",
        status = HttpStatus.CONFLICT.value(),
        details = mutableMapOf(ex.cause.toString() to ex.message),
        exception = ex.javaClass.toString(),
        timestamp = LocalDateTime.now()
      )
    )
  }

  @ExceptionHandler(BusinessException::class)
  fun validExceptionHandler(ex: BusinessException): ResponseEntity<ExceptionDetails> {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionDetails(
        title = "Bad Request | Consult the documentation ",
        status = HttpStatus.CONFLICT.value(),
        details = mutableMapOf(ex.cause.toString() to ex.message),
        exception = ex.javaClass.toString(),
        timestamp = LocalDateTime.now()
    )
    )
  }

  @ExceptionHandler(IllegalArgumentException::class)
  fun validExceptionHandler(ex: IllegalArgumentException): ResponseEntity<ExceptionDetails> {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionDetails(
        title = "Bad Request | Consult the documentation ",
        status = HttpStatus.CONFLICT.value(),
        details = mutableMapOf(ex.cause.toString() to ex.message),
        exception = ex.javaClass.toString(),
        timestamp = LocalDateTime.now()
    )
    )
  }
}