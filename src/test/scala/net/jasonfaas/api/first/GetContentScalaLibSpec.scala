package net.jasonfaas.api.first

import java.io.FileInputStream
import java.util.Properties

import net.jasonfaas.api.{JsonFormatHelper, RestApiCallHelper}

import scalaj.http.{HttpResponse, Http}


import org.scalatest.FunSpec

/**
  * Created by jasonfaas on 8/14/16.
  */
class GetContentScalaLibSpec extends FunSpec {


  describe("Trying to get rest content through scala library") {
    it("First Attempt, with likely bad url") {

      val string: HttpResponse[String] = Http("http://foo.com/search").param("q", "monkeys").asString

      println(s"a:$string:b")

      assert(!string.toString.contains("200"))
      assert(string.toString.contains("301 Moved Permanently"))
    }

    it("Second Attempt, with Authorization") {

      val url: String = "https://videogamesrating.p.mashape.com/get.php?count=5&game=God+of+War"
      val string: HttpResponse[String] = Http(url)
        .header("X-Mashape-Key", new RestApiCallHelper().getApiKey("mashable.ign.ratings"))
        .header("Accept", "application/json")
        .asString

      val stringTwo: String = new JsonFormatHelper().toJsonFormattedString(string.toString)

//      println(s"a:$string:b")
      println(s"$stringTwo")

      assert(stringTwo.contains("Sony Computer Entertainment"))
    }

    it("get api keys from properties file") {
      val key: String = new RestApiCallHelper().getApiKey("mashable.ign.ratings")

      println(s"$key")
    }
  }

}
