package com.example.empresas_android

import com.example.empresas_android.ui.helper.*
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `validate WHEN value has number MUST return true`() {
        assertTrue("apoio2".hasNumber())
    }

    @Test
    fun `validate WHEN value has not number MUST return false`(){
        assertFalse("senha".hasNumber())
    }

    @Test
    fun `validate WHEN value has uppercase MUST return true`() {
        assertTrue("Senha".hasUpperCase())
    }

    @Test
    fun `validate WHEN value has not uppercase MUST return false`() {
        assertFalse("senha".hasUpperCase())
    }

    @Test
    fun `validate WHEN value has lowercase MUST return true`() {
        assertTrue("Senha".hasLowerCase())
    }

    @Test
    fun `validate WHEN value has not lowercase MUST return false`() {
        assertFalse("SENHA".hasLowerCase())
    }

    @Test
    fun `validate WHEN value has special character MUST return true`() {
        assertTrue("Senha#".hasEspecialCharacters())
    }

    @Test
    fun `validate WHEN value has not especial character MUST return false`() {
        assertFalse("senha".hasEspecialCharacters())
    }

    @Test
    fun `validate WHEN value has six character MUST return true`() {
        assertTrue("Senha123".hasSixCharacter())
    }

    @Test
    fun `validate WHEN value has not six character MUST return false`() {
        assertFalse("senha".hasSixCharacter())
    }

    @Test
    fun `validate WHEN email is valid MUST return true`() {
        assertTrue("email@gmail.com".isEmail())
    }

    @Test
    fun `validate WHEN email has not At Sign MUST return false`() {
        assertFalse("emailgmail.com".isEmail())
    }

    @Test
    fun `validate WHEN email has not dot MUST return false`() {
        assertFalse("e.mail@gmailcom".isEmail())
    }

    @Test
    fun `validate WHEN email has too much At Sign MUST return false`() {
        assertFalse("email@gmail@com".isEmail())
    }

}
