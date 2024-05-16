//linear regression to see if number of population/retail_sum relate to crime index

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.linalg.Vectors

// Load the data
val data = spark.read
  .option("header", "true")
  .option("inferSchema", "true")
  .csv("modified_data.csv") 

// Prepare the data for regression analysis using the correct column name
val assembler = new VectorAssembler()
  .setInputCols(Array("retailpop"))  
  .setOutputCol("features")

val output = assembler.transform(data)
val regressionData = output.select($"features", $"Index_Total_sum".alias("label"))

// Set up the linear regression model
val lr = new LinearRegression() 
  .setMaxIter(10)
  .setRegParam(0.3)
  .setElasticNetParam(0.8)

// Fit the model
val lrModel = lr.fit(regressionData)

// Print the model coefficients and intercept
println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

// Summary of the model
val trainingSummary = lrModel.summary
println(s"numIterations: ${trainingSummary.totalIterations}")
println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
println(s"r2: ${trainingSummary.r2}")