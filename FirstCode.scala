#finding mode
val outputPath = "/user/hl4070_nyu_edu/output_unique/part-r-00000"
case class ZipCodeCount(zipCode: String, count: Int)

val zipCodeCountsDF = spark.read.textFile(outputPath).map(line => {
    val parts = line.split("\t")
    ZipCodeCount(parts(0), parts(1).toInt)
}).toDF()

import org.apache.spark.sql.functions._

val modeDF = zipCodeCountsDF.groupBy("zipCode").agg(count("count").alias("freq")).orderBy(desc("freq"))
val mode = modeDF.first().getAs[String]("zipCode")
println(s"Mode (most frequent zip code): $mode")

#calculating median 
val median = zipCodeCountsDF.stat.approxQuantile("count", Array(0.5), 0).head
println(s"Median count of occurrences: $median")

#calculating standard deviation 
val stddevCount = zipCodeCountsDF.agg(stddev("count").alias("stddev_count")).first().getAs[Double]("stddev_count")
println(s"Standard deviation of the count: $stddevCount")

#Text Formatting: 
## Normalizes a text column by removing leading and trailing spaces and converting the text to lowercase. 
This is useful for future joining or matching operations where consistent text formatting is crucial.
# creating a binary column
##Adds a new binary column (highCountBinary) based on the condition of another column (count), a binary column that flags whether the count of a zip code is higher than 100.

import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession

val spark: SparkSession = SparkSession.builder().appName("Data Cleaning Example").getOrCreate()
import spark.implicits._

case class ZipCodeCount(zipCode: String, count: Int)

val zipCodeCountsDF = Seq(
  ZipCodeCount(" 12345 ", 150),
  ZipCodeCount("67890", 50),
  ZipCodeCount(" 101112 ", 200)
).toDF()

// Text formatting - Removing spaces and converting to lowercase
val cleanedDF = zipCodeCountsDF
  .withColumn("zipCode", lower(trim($"zipCode"))) // Removing leading/trailing spaces and making all lowercase

// Creating a binary column based on the count
val enrichedDF = cleanedDF
  .withColumn("highCountBinary", when($"count" > 100, 1).otherwise(0)) // Creating binary column based on count

enrichedDF.show()
