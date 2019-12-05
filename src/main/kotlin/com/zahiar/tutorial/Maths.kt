package com.zahiar.tutorial

import org.http4k.core.*
import org.http4k.filter.ServerFilters
import org.http4k.lens.Query
import org.http4k.lens.int
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.Netty
import org.http4k.server.asServer

fun MyMathsServer(port: Int): Http4kServer = MyMathsApp().asServer(Netty(port))

fun MyMathsApp() = ServerFilters.CatchLensFailure.then(
    routes(
        "/ping" bind Method.GET to { _: Request -> Response(Status.OK) },
        "/add" bind Method.GET to {
            val valuesToAdd = Query.int().multi.defaulted("value", listOf())(it)
            Response(Status.OK).body(valuesToAdd.sum().toString())
        }
    )
)