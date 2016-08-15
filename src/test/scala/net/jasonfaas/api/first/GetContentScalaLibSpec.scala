package net.jasonfaas.api.first

import java.io.FileInputStream
import java.util.Properties

import scalaj.http.{HttpResponse, Http}


import org.scalatest.FunSpec

/**
  * Created by jasonfaas on 8/14/16.
  */
class GetContentScalaLibSpec extends FunSpec {

  def getTabs(tabCount: Int) = {
    val buffer: StringBuffer = new StringBuffer("")
    for( a <- 0 to tabCount){
      buffer.append("\t")
    }
    buffer.toString
  }

  def toJsonFormattedString(toString: String): String = {
    val buffer: StringBuffer = new StringBuffer("")
    var tabCount = 0;
    for (nextChar <- toString) {


      if ((nextChar == '[') || (nextChar == '{')) {

        buffer.append(s"$nextChar")
        buffer.append("\n")
        tabCount = tabCount + 1
        buffer.append(s"${getTabs(tabCount)}")

      } else if ((nextChar == ']') || (nextChar == '}')) {
        buffer.append("\n")
        tabCount = tabCount - 1
        buffer.append(s"${getTabs(tabCount)}")


        buffer.append(s"$nextChar")
      } else {

        buffer.append(s"$nextChar")
      }

      if (nextChar == ',') {
        buffer.append(s"${getTabs(tabCount)}")
      }
    }

    buffer.toString.replace(",", ",\n")
  }

  def getApiKey(s: String) = {
    var property = ""
    try {
      val prop = new Properties()
      prop.load(new FileInputStream("/Users/jasonfaas/Desktop/apikeys.properties"))

      property = prop.getProperty("mashable.ign.ratings")

    } catch { case e: Exception =>
      e.printStackTrace()
    }

    property
  }

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
        .header("X-Mashape-Key", getApiKey("mashable.ign.ratings"))
        .header("Accept", "application/json")
        .asString

      val stringTwo: String = toJsonFormattedString(string.toString)

//      println(s"a:$string:b")
      println(s"$stringTwo")

      assert(stringTwo.contains("Sony Computer Entertainment"))
    }

    it("get api keys from properties file") {
      val key: String = getApiKey("mashable.ign.ratings")

      println(s"$key")
    }
  }

}
