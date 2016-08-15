package net.jasonfaas.api.elo.rushb

import net.jasonfaas.api.{JsonFormatHelper, RestApiCallHelper}
import org.scalatest.FunSpec

import scalaj.http.{Http, HttpResponse}

/**
  * Created by jasonfaas on 8/14/16.
  */
class RushbEloGameSpec extends FunSpec {


  describe("SimpleEloGame") {
    it("Game Lists: Create Game, Delete Game") {
      val createGameUrl: String = "https://akdarrah-rushb-elo-engine-v1.p.mashape.com/1/games"
      val createGameResponse: HttpResponse[String] = Http(createGameUrl)
        .header("X-Mashape-Key", new RestApiCallHelper().getApiKey("mashable.everything"))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Accept", "application/json")
        .param("game[default_k_factor]", "15")
        .param("game[default_rating]", "1000")
        .param("game[pro_rating_boundry]", "2400")
        .param("game[starter_boundry]", "30")
        .asString
      val createGameResponseString: String = new JsonFormatHelper().toJsonFormattedString(createGameResponse.toString)
      println(s"$createGameResponseString")

      println(":::::::::");

      val gameIndexUrl: String = "https://akdarrah-rushb-elo-engine-v1.p.mashape.com/1/games"
      val gameIndexesResponse: HttpResponse[String] = Http(gameIndexUrl)
        .header("X-Mashape-Key", new RestApiCallHelper().getApiKey("mashable.everything"))
        .header("Accept", "application/json")
        .asString
      val gameIndexesResponseString: String = new JsonFormatHelper().toJsonFormattedString(gameIndexesResponse.toString)
      println(s"$gameIndexesResponseString")

//      assert(createGameResponseString.contains("Sony Computer Entertainment"))
    }

    it("Create Game, 2 People, 1 Match, Get Results") {

    }
  }

}
