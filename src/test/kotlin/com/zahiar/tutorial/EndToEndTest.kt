package com.zahiar.tutorial

import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.client.OkHttp
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EndToEndTest {

    private val client = OkHttp()
    private val server = MyMathsServer(0)

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }

    @Test
    fun `responds to ping`() {
        val result = client(Request(Method.GET, "http://localhost:${server.port()}/ping"))
        assertThat(result, hasStatus(Status.OK))
    }

    @Test
    fun `responds to request to 'add' endpoint`() {
        val result = client(Request(Method.GET, "http://localhost:${server.port()}/add?value=1&value=2"))
        assertThat(result, hasStatus(Status.OK))
        assertThat(result, hasBody("3"))
    }

    @Test
    fun `responds to request to 'multiply' endpoint`() {
        val result = client(Request(Method.GET, "http://localhost:${server.port()}/multiply?value=1&value=3"))
        assertThat(result, hasStatus(Status.OK))
        assertThat(result, hasBody("3"))
    }

}