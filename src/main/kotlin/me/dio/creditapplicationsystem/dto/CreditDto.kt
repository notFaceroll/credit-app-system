package me.dio.creditapplicationsystem.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.entities.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto (
    @field:NotNull(message = "Invalid input") val creditValue: BigDecimal,
    @field:Future(message = "Invalid Date") val dayFirstInstallment: LocalDate,
    @field:Min(value = 3, message = "Must be higher than 2") val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid input") val customerId: Long
){
  fun toEntity() : Credit {
    return Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
  }
}
