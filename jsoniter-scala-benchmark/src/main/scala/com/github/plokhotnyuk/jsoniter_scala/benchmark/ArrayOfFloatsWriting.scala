package com.github.plokhotnyuk.jsoniter_scala.benchmark

import java.nio.charset.StandardCharsets.UTF_8

import com.avsystem.commons.serialization.json._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.CirceEncodersDecoders._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.DslPlatformJson._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.JacksonSerDesers._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.JsoniterScalaCodecs._
//import com.github.plokhotnyuk.jsoniter_scala.benchmark.SprayFormats._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.UPickleReaderWriters._
import com.github.plokhotnyuk.jsoniter_scala.core._
//import com.jsoniter.output.JsoniterJavaSerializer
//import io.circe.parser._
import io.circe.syntax._
import org.openjdk.jmh.annotations.Benchmark
//import play.api.libs.json.Json
//import spray.json._

class ArrayOfFloatsWriting extends ArrayOfFloatsBenchmark {
  @Benchmark
  def writeAVSystemGenCodec(): Array[Byte] = JsonStringOutput.write(obj).getBytes(UTF_8)

  @Benchmark
  def writeCirce(): Array[Byte] = printer.pretty(obj.asJson).getBytes(UTF_8)

  @Benchmark
  def writeDslJsonScala(): Array[Byte] = dslJsonEncode(obj)

  @Benchmark
  def writeJacksonScala(): Array[Byte] = jacksonMapper.writeValueAsBytes(obj)
/* FIXME: PreciseFloatSupport.enable() doesn't work sometime and Jsoniter Java serializes values rounded to 6 digits
  @Benchmark
  def writeJsoniterJava(): Array[Byte] = JsoniterJavaSerializer.serialize(obj)
*/
  @Benchmark
  def writeJsoniterScala(): Array[Byte] = writeToArray(obj)

  @Benchmark
  def writeJsoniterScalaPrealloc(): Int = writeToSubArray(obj, preallocatedBuf, 0, preallocatedBuf.length)
/* FIXME: Play-JSON serializes double values instead of float
  @Benchmark
  def writePlayJson(): Array[Byte] = Json.toBytes(Json.toJson(obj))
*/
/* FIXME: Spray-JSON serializes double values instead of float
  @Benchmark
  def writeSprayJson(): Array[Byte] = obj.toJson.compactPrint.getBytes(UTF_8)
*/
  @Benchmark
  def writeUPickle(): Array[Byte] = write(obj).getBytes(UTF_8)
}