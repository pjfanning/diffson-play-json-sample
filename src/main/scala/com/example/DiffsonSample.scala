package com.example

import diffson._
import diffson.lcs._
import diffson.playJson._
import diffson.playJson.DiffsonProtocol._
import diffson.jsonpatch.lcsdiff._
import play.api.libs.json.{JsValue, Json}

object DiffsonSample extends App {
  implicit val lcs = new Patience[JsValue]

  val json1 = Json.parse("""{
                      |  "a": 1,
                      |  "b": true,
                      |  "c": ["test", "plop"]
                      |}""".stripMargin)

  val json2 = Json.parse("""{
                      |  "a": 6,
                      |  "c": ["test2", "plop"],
                      |  "d": false
                      |}""".stripMargin)

  val diff0 = diff(json1, json2)
  val pp0 = Json.prettyPrint(Json.toJson(diff0))
  println(pp0)

}
