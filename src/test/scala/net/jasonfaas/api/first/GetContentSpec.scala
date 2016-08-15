package net.jasonfaas.api.first

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import scala.xml.XML
import scala.collection.mutable.StringBuilder
import java.io._
import org.scalatest.FunSpec

/**
  * Created by jasonfaas on 8/13/16.
  */
class GetContentSpec extends FunSpec {

  describe("First Api Calls") {
    it("first example copied") {
      // (1) get the content from the yahoo weather api url
      val content = getRestContent("http://weather.yahooapis.com/forecastrss?p=80020&u=f")

      // (2) convert it to xml
      val xml = XML.loadString(content)
      assert(xml.isInstanceOf[scala.xml.Elem])  // needed?

      // (3) search the xml for the nodes i want
      val temp = (xml \\ "channel" \\ "item" \ "condition" \ "@temp") text
      val text = (xml \\ "channel" \\ "item" \ "condition" \ "@text") text

      // (4) print the results
      val currentWeather = s"The current temperature is $temp degrees, and the sky is ${text.toLowerCase}."
      println(currentWeather)
    }
  }

  def getRestContent(url:String): String = {
    val httpClient = new DefaultHttpClient()
    val httpResponse = httpClient.execute(new HttpGet(url))
    val entity = httpResponse.getEntity()
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent()
      content = io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
    httpClient.getConnectionManager().shutdown()
    return content
  }
}
