package com.zahiar.tutorial

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.server.Http4kServer
import org.http4k.server.Netty
import org.http4k.server.asServer

fun MyMathsServer(port: Int): Http4kServer = { _: Request -> Response(Status.OK) }.asServer(Netty(port))