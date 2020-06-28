package com.github.plokhotnyuk.jsoniter_scala.benchmark

class ArrayOfOffsetTimesWritingSpec extends BenchmarkSpecBase {
  def benchmark: ArrayOfOffsetTimesWriting = new ArrayOfOffsetTimesWriting {
    setup()
  }
  
  "ArrayOfOffsetTimesWriting" should {
    "write properly" in {
      val b = benchmark
      toString(b.avSystemGenCodec()) shouldBe b.jsonString
      toString(b.borer()) shouldBe b.jsonString
      toString(b.circe()) shouldBe b.jsonString
      toString(b.dslJsonScala()) shouldBe b.jsonString
      toString(b.jacksonScala()) shouldBe b.jsonString
      toString(b.jsoniterScala()) shouldBe b.jsonString
      toString(b.preallocatedBuf, 0, b.jsoniterScalaPrealloc()) shouldBe b.jsonString
      toString(b.playJson()) shouldBe b.jsonString
      toString(b.sprayJson()) shouldBe b.jsonString
      toString(b.uPickle()) shouldBe b.jsonString
    }
  }
}