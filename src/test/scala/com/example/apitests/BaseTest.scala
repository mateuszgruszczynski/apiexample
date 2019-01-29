package com.example.apitests

import org.scalatest.{AppendedClues, BeforeAndAfterAll, FlatSpec, Matchers}

class BaseTest extends FlatSpec with Matchers with AppendedClues with BeforeAndAfterAll{

  override def beforeAll(): Unit = {
    RestAssuredConfig.initialize
  }

}
