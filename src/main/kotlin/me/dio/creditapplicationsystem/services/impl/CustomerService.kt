package me.dio.creditapplicationsystem.services.impl

import me.dio.creditapplicationsystem.entities.Customer
import me.dio.creditapplicationsystem.repositories.CustomerRepository
import me.dio.creditapplicationsystem.services.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {
  override fun save(customer: Customer): Customer {
    return this.customerRepository.save(customer)
  }

  override fun findById(id: Long): Customer {
    return this.customerRepository.findById(id).orElseThrow {
      throw RuntimeException("Id $id not found.")
    }
  }

  override fun delete(id: Long) {
    this.customerRepository.deleteById(id)
  }

}