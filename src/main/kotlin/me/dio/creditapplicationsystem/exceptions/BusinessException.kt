package me.dio.creditapplicationsystem.exceptions

data class BusinessException(override val message: String?) : RuntimeException(message)
