# Insurance Claim Analyzer
This project is an end-to-end solution for insurance claim management, designed to:

Collect claim data via a web form.

Persist claim information in a PostgreSQL database.- 

Use Kafka to stream claim messages for further processing.

Process claims using Apache Spark, flagging suspicious claims (e.g., those above a certain threshold).

Display claims in a web frontend built with React.

## Project Structure
```
insurance-claim-analyzer/
├── claim-api/                # Spring Boot backend (handles claims API)
│   ├── Dockerfile
│   ├── application.yml
│   └── src/
├── spark-processor/          # Spark job (processes claims in real-time)
│   ├── Dockerfile
│   ├── build.sbt
│   └── src/
├── frontend/                 # React app (view and submit claims)
│   ├── Dockerfile
│   ├── package.json
│   ├── src/
└── docker-compose.yml        # Orchestrates all services (PostgreSQL, Kafka, backend, frontend)
```
## Technologies Used
- Spring Boot for the backend (Java)

- Apache Kafka for real-time streaming

- Apache Spark for data processing

- PostgreSQL for data storage

- React (with Vite) for the frontend

- Docker for containerization and orchestration

## Setup Instructions
### 1. Clone the Repository
```
git clone https://github.com/yourusername/insurance-claim-analyzer.git
cd insurance-claim-analyzer
```
### 2. Claim API Setup
```
cd claim-api
mvn clean package
```
### 3. Spark Processor Setup
```
cd spark-processor
sbt package
```
### 4. Docker Setup
Make sure you have Docker and Docker Compose installed. Then, run the following command to build and start all the containers:
```
docker-compose up --build
```
This will start the following services:

- **Claim API** (Spring Boot) on port `8080`

- **PostgreSQL** for persistent storage (on port `5432`)

- **Kafka** for streaming (on port `9092`)

- **Frontend** (React) on port `3000`
### 5. Visit the Application
Once the containers are up, navigate to the following URLs:

- **Frontend**: http://localhost (Submit and view claims)

- **Claim API**: http://localhost:8080 (Access claims via REST API)

### 6. View and Submit Claims
- **Submit a new claim**: Use the form on the frontend to create a new claim.

- **View all claims**: The list of claims will appear below the form on the same page.

### 7. Monitor Spark Processing (Optional)
To view the processing of suspicious claims by Apache Spark, look at the logs of the `spark-processor` container or adapt the Spark job to output results to a different storage or system.
## Services
### Claim API (Spring Boot)
Handles RESTful endpoints for claims:

- `GET /claims`: Retrieve all claims.

- `POST /claims`: Submit a new claim.

### Spark Processor
Processes incoming claim data in real-time:

- Listens to the Kafka topic `claims-input`.

- Flags claims with an amount greater than `5000` as suspicious.

### Frontend (React)
A simple interface to:

- Submit new claims via a form.

- Display all existing claims.
## Kafka Integration
Claims are streamed in real-time to a Kafka topic (`claims-input`). The **Spark processor** listens to this topic, performs any necessary processing (such as flagging high-value claims), and logs the results.
## Database (PostgreSQL)
The claim data is persisted in a PostgreSQL database with the following structure:

- **Claims Table**: Stores all claim records (`policyNumber`, `description`, `amount`, `createdAt`).
## Next Steps
1. **Enhance Spark Processing**: Add more complex logic to the Spark job for further analysis of the claims, such as fraud detection or categorization.

2. **Extend the API**: Add more functionality, such as updating or deleting claims.

3. **Improve Frontend**: Make the frontend more user-friendly and add features like claim search, filtering, or sorting.

4. **Testing and CI/CD**: Integrate automated tests and set up Continuous Integration and Deployment for easier updates and improvements.
## Contributing
Feel free to fork this project, create pull requests, or raise issues. Contributions are welcome!
## License
This project is licensed under the MIT License - see the LICENSE file for details.