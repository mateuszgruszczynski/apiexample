package com.example.apitests

class SampleTest extends BaseTest {

  behavior of "Example api"

  it should "return single todo ticket" in {

    val ticketResponse = SampleClient.getTodo(1)

    ticketResponse.status shouldBe 200
    val responseBody = ticketResponse.getBodyAs[TodoTicket]

    responseBody.completed shouldBe false withClue "Invalid completion status"

  }

  it should "return all tickets" in {

    val allTicketsResponse = SampleClient.getAllTodos

    allTicketsResponse.status shouldBe 200
    val responseBody = allTicketsResponse.getBodyAs[List[TodoTicket]]

    responseBody should not be empty withClue "Returned empty list"

  }
}

