package me.dio.creditapplicationsystem.dto

import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.entities.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto (
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customerId: Long
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
