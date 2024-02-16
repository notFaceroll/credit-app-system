package me.dio.creditapplicationsystem.services

import me.dio.creditapplicationsystem.entities.Customer

interface ICustomerService {
  fun save(customer: Customer): Customer

  fun findById(id: Long): Customer

  fun delete(id: Long)
}