package com.example.apitests

import io.restassured.filter.{Filter, FilterContext}
import io.restassured.response.Response
import io.restassured.specification.{FilterableRequestSpecification, FilterableResponseSpecification}

// Dummy filter that just prints request and response
object RequestPrintingFilter extends Filter{

  override def filter(requestSpec: FilterableRequestSpecification, responseSpec: FilterableResponseSpecification, ctx: FilterContext): Response = {
    println("=[REQUEST]=================================================")
    println(s"${requestSpec.getMethod} ${requestSpec.getURI})\n")
    println("=[RESPONSE]=================================================")
    val response = ctx.next(requestSpec, responseSpec)
    println(response.getStatusCode)
    println(response.getBody.print() + "\n")
    response
  }

}