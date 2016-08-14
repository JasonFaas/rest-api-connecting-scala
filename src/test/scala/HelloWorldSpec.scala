
import org.scalatest.FunSpec

import scala.collection.mutable.ListBuffer

class HelloWorldSpec extends FunSpec {
  describe("Adding 1 to 1") {
    it("should equals 2"){
      assert(1+1 == 2)
    }

    it("Should call another method") {
      assert(HelloWorld.getHelloWorld() == "Hello World");
    }
  }

//  describe("Get Hello World") {
//    it("should return hello world") {
//      assert(HelloWorld.)
//    }
//  }


  def fixture =
    new {
      val builder = new StringBuilder("ScalaTest is ")
      val buffer = new ListBuffer[String]
    }

  describe("Testing") {

    it("should be easy") {
      val f = fixture
      f.builder.append("easy!")
      assert(f.builder.toString === "ScalaTest is easy!")
      assert(f.buffer.isEmpty)
      f.buffer += "sweet"
    }

    it("should be fun") {
      val f = fixture
      f.builder.append("fun!")
      assert(f.builder.toString === "ScalaTest is fun!")
      assert(f.buffer.isEmpty)
    }
  }

}

