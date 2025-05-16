FROM bitnami/spark:3.5

COPY target/scala-2.12/spark-processor.jar /opt/bitnami/app/spark-processor.jar

# Permitir usar entrypoint con args
ENTRYPOINT [ "/opt/bitnami/spark/bin/spark-submit", "--class", "com.example.ClaimProcessor" ]
CMD [ "--master", "local[*]", "/opt/bitnami/app/spark-processor.jar" ]
