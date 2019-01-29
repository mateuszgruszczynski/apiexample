package com.example.apitests

import java.lang.reflect.Type

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.ISO8601DateFormat
import io.restassured.RestAssured
import io.restassured.config.{HttpClientConfig, ObjectMapperConfig}
import io.restassured.mapper.factory.{DefaultJackson2ObjectMapperFactory, Jackson2ObjectMapperFactory}

import scala.collection.JavaConverters._

object RestAssuredConfig {

  lazy val initialize = {
    RestAssured.reset()
    RestAssured.filters(RequestPrintingFilter)
    RestAssured.config = RestAssured.config()
      .httpClient(HttpClientConfig.httpClientConfig().addParams(Map("http.socket.timeout" -> 30000, "http.connection.timeout" -> 30000).asJava))
      .objectMapperConfig(
        ObjectMapperConfig
          .objectMapperConfig()
          .jackson2ObjectMapperFactory(
            new Jackson2ObjectMapperFactory {
              override def create(`type`: Type, s: String): ObjectMapper = {
                new DefaultJackson2ObjectMapperFactory().create(`type`, s).setSerializationInclusion(JsonInclude.Include.NON_NULL).findAndRegisterModules().setDateFormat(new ISO8601DateFormat())
              }
            }
          )
      )
  }
}
