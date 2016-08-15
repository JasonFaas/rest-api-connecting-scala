package net.jasonfaas.api

import java.io.FileInputStream
import java.util.Properties

/**
  * Created by jasonfaas on 8/14/16.
  */
class RestApiCallHelper {


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
}
