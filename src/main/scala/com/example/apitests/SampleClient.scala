package com.example.apitests

import io.restassured.RestAssured._
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse
import io.restassured.response.{Response, ResponseBodyExtractionOptions}

import scala.reflect._
import _root_.io.restassured.http.Headers

// Custom class for response with some extra methods
case class ExtractedResponse(
  status: Int,
  headers: Headers,
  body: ResponseBodyExtractionOptions
){
  def getBodyAs[T: ClassTag] : T = body.as(classTag[T].runtimeClass).asInstanceOf[T]
  def isSuccess = (status == 200 || status == 201)
}

abstract class BaseClient {

  // Just to convert rest assured response to our object with custom methods
  def extractResponse(response: Response) = ExtractedResponse(
    response.getStatusCode,
    response.headers(),
    response.Then().extract().body()
  )

}

object SampleClient extends BaseClient{

  val todosUrl = "https://jsonplaceholder.typicode.com/todos"
  val todoByIdUrl = todosUrl + "/{todoId}"

  // Returns bare response
  // It could use Then()... to extract payload, check status but it wont work if we expect both positive and negative results which have different format
  // Its better to handle extraction on test level using methods from classes above
  def getTodo(id: Int) = extractResponse(
    given()
      .pathParam("todoId", id)
      .when()
      .get(todoByIdUrl)
  )

  def getAllTodos = extractResponse(
    given()
      .when()
      .get(todosUrl)
  )
}