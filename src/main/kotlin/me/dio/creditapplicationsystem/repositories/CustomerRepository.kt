package me.dio.creditapplicationsystem.repositories

import me.dio.creditapplicationsystem.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
}