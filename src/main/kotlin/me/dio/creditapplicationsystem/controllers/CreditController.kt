package me.dio.creditapplicationsystem.controllers

import jakarta.validation.Valid
import me.dio.creditapplicationsystem.dto.CreditDto
import me.dio.creditapplicationsystem.dto.CreditView
import me.dio.creditapplicationsystem.dto.CreditViewList
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.services.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {
  @PostMapping
  fun store(@RequestBody @Valid creditDto: CreditDto) : ResponseEntity<String> {
    val storedCredit: Credit = this.creditService.save(creditDto.toEntity())
    return ResponseEntity.status(HttpStatus.CREATED).body("Credit ${storedCredit.creditCode} saved successfully.")
  }

  @GetMapping
  fun index(@RequestParam id: Long) : ResponseEntity<List<CreditViewList>> {
//    val creditList: List<Credit> = this.creditService.findAllByCustomer(id)
//    val mappedCreditList: List<CreditViewList> = creditList.map { credit -> CreditViewList(credit) }
//    return mappedCreditList
    val creditViewList: List<CreditViewList> = this.creditService.findAllByCustomer(id).stream()
        .map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())
    return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
  }

  @GetMapping("/{id}")
  fun show(@PathVariable id: UUID, @RequestParam customerId: Long) : ResponseEntity<CreditView> {
    val foundCredit: Credit = this.creditService.findByCreditCode(customerId, id)
    return ResponseEntity.status(HttpStatus.OK).body(CreditView(foundCredit))
  }
}