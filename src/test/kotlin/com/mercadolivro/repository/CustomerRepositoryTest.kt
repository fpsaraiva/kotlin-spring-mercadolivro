package com.mercadolivro.repository

import com.mercadolivro.helper.buildCustomer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

/*
* Atenção: Teste irá quebrar devido a exception SQL.
* Para funcionar, o (name = "role") do atributo "roles" da classe "CustomerModel" deve ser deletado.
* Deixei padrão para não alterar estrutura banco.
* */

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @Test
    fun `should return name containing`() {
        val fernando = customerRepository.save(buildCustomer(name = "Fernando"))
        val marcia = customerRepository.save(buildCustomer(name = "Marcia"))
        val maria = customerRepository.save(buildCustomer(name = "Maria"))

        val customers = customerRepository.findByNameContaining("Ma")

        assertEquals(listOf(marcia, maria), customers)
    }

    @Nested
    inner class `find by email` {
        @Test
        fun `should return customer when email exists`() {
            val email = "email@email.com"
            val customer = customerRepository.save(buildCustomer(email = email))

            val result = customerRepository.findByEmail(email)

            assertNotNull(result)
            assertEquals(customer, result)
        }

        @Test
        fun `should return null when email does no exist`() {
            val email = "nonexist@email.com"

            val result = customerRepository.findByEmail(email)

            assertNull(result)
        }
    }

    @Nested
    inner class `exists by email` {
        @Test
        fun `should return true when email exists`() {
            val email = "email@email.com"
            customerRepository.save(buildCustomer(email = email))

            val exists = customerRepository.existsByEmail(email)

            assertTrue(exists)
        }

        @Test
        fun `should return false when email does no exist`() {
            val email = "nonexist@email.com"

            val exists = customerRepository.existsByEmail(email)

            assertFalse(exists)
        }
    }
}