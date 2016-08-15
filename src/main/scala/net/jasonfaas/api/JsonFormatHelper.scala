package net.jasonfaas.api

/**
  * Created by jasonfaas on 8/14/16.
  */
class JsonFormatHelper {

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

  private def getTabs(tabCount: Int) = {
    val buffer: StringBuffer = new StringBuffer("")
    for( a <- 0 to tabCount){
      buffer.append("\t")
    }
    buffer.toString
  }

}
