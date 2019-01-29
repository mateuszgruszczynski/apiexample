package com.example.apitests

import org.json4s._

case class TodoTicket(
  userId: Int,
  id: Int,
  title: String,
  completed: Boolean
)