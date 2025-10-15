# Diversidade - Corporate Diversity and Inclusion Microservice

A Spring Boot microservice for managing corporate diversity and inclusion initiatives, including training programs and metrics tracking.

## Features

- Training program management
- Training metrics tracking
- RESTful API with OpenAPI documentation
- Oracle database integration
- Docker containerization

## Prerequisites

- Java 21
- Maven
- Docker and Docker Compose
- Oracle Database (or use the provided Docker container)

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/diversidade.git
cd diversidade
```

2. Build the project:
```bash
./mvnw clean package
```

3. Start the application using Docker Compose:
```bash
docker-compose up -d
```

The application will be available at `http://localhost:8080`

## API Documentation

The API documentation is available through Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

### Training API Endpoints

- `GET /api/trainings` - Get all trainings
- `GET /api/trainings/{id}` - Get training by ID
- `POST /api/trainings` - Create new training
- `PUT /api/trainings/{id}` - Update training
- `DELETE /api/trainings/{id}` - Delete training

### Metrics API Endpoints

- `GET /api/metrics` - Get all metrics
- `GET /api/metrics/{id}` - Get metrics by ID
- `POST /api/metrics` - Create new metrics
- `PUT /api/metrics/{id}` - Update metrics
- `DELETE /api/metrics/{id}` - Delete metrics

## Database Schema

### Training Table
- id (PK)
- title
- description
- start_date
- end_date
- status
- created_at
- updated_at

### Metrics Table
- id (PK)
- training_id (FK)
- participant_count
- completion_rate
- satisfaction_score
- feedback_count
- created_at
- updated_at

## Development

### Running Tests
```bash
./mvnw test
```

### Database Migrations
The project uses Flyway for database migrations. Migration scripts are located in `src/main/resources/db/migration/`.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details. 