package com.example

import diffson._
import diffson.jsonpatch.JsonPatch
import diffson.jsonpatch.lcsdiff._
import diffson.lcs._
import io.kontainers.diffson.playJson.DiffsonProtocol._
import io.kontainers.diffson.playJson._
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

  println("generate diff")
  val diff0 = diff(json1, json2)
  val jsonPath0 = Json.toJson(diff0)
  println(Json.prettyPrint(jsonPath0))

  println("apply diff as patch to json1 - should yield same json as json2")
  val patch0 = jsonPath0.validate[JsonPatch[JsValue]].get
  val json20 = patch0(json1).get
  println(Json.prettyPrint(json20))
}
