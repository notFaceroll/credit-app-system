package me.dio.creditapplicationsystem.services.impl

import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.exceptions.BusinessException
import me.dio.creditapplicationsystem.repositories.CreditRepository
import me.dio.creditapplicationsystem.services.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService,
) : ICreditService {
  override fun save(credit: Credit): Credit {
    credit.apply {
      customer = customerService.findById(credit.customer?.id!!)
    }
    return this.creditRepository.save(credit)
  }

  override fun findAllByCustomer(customerId: Long): List<Credit> {
    return this.creditRepository.findAllByCustomer(customerId)
  }

  override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
    val credit: Credit = this.creditRepository.findByCreditCode(creditCode)
        ?: throw BusinessException("CreditCode not found.")
    return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact Admin")
  }
}