package com.zahiar.tutorial

import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

class MultiplyFunctionalTest {

    private val client = MyMathsApp()

    @Test
    fun `multiplies values together`() {
        val result = client(Request(Method.GET, "/multiply?value=5&value=5"))
        assertThat(result, hasStatus(Status.OK))
        assertThat(result, hasBody("25"))
    }

    @Test
    fun `answer is zero when no values`() {
        val result =  client(Request(Method.GET, "/multiply"))
        assertThat(result, hasStatus(Status.OK))
        assertThat(result, hasBody("0"))
    }

    @Test
    fun `bad request when some values are not numbers`() {
        val result = client(Request(Method.GET, "/multiply?value=1&value=notANumber"))
        assertThat(result, hasStatus(Status.BAD_REQUEST))
    }

}