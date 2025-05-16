package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object ClaimProcessor {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Claim Processor")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val kafkaDF = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "kafka:9092")
      .option("subscribe", "claims-input")
      .option("startingOffsets", "latest")
      .load()

    val claims = kafkaDF
      .selectExpr("CAST(value AS STRING) as claimId")
      .as[String]

    // Leer datos de PostgreSQL
    val claimTable = spark.read
      .format("jdbc")
      .option("url", "jdbc:postgresql://postgres:5432/claims_db")
      .option("dbtable", "claim")
      .option("user", "user")
      .option("password", "password")
      .load()

    val enriched = claims.join(claimTable, $"claimId" === claimTable("id").cast("string"))

    val suspicious = enriched.filter($"amount" > 5000)

    val query = suspicious
      .select($"policyNumber", $"amount")
      .writeStream
      .outputMode("append")
      .format("console")
      .start()

    query.awaitTermination()
  }
}
