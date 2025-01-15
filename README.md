# ExpenseTracker

A full-stack expense tracking application that helps users manage their personal expenses efficiently. Built with React frontend and Spring Boot backend, containerized with Docker, and orchestrated using Kubernetes.

## Features

- User authentication and authorization using JWT
- Personal expense tracking and management
- Expense modification and deletion
- Total expenditure visualization
- Containerized deployment
- Horizontal Pod Autoscaling
- Continuous monitoring with ELK Stack

## Tech Stack

### Frontend
- React
- Docker

### Backend
- Spring Boot
- PostgreSQL
- JWT Authentication
- JUnit 5 for testing

### DevOps & Infrastructure
- Docker & Docker Compose
- Kubernetes
- Jenkins CI/CD
- Ansible
- ELK Stack for monitoring
- Maven for build automation
- Ngrok for localhost tunneling

## Getting Started

### Prerequisites
- Docker and Docker Compose
- Java 11 or higher
- Node.js and npm
- Maven
- Kubernetes cluster (for K8s deployment)

### Local Development Setup

1. Clone the repository
```bash
git clone https://github.com/pink-27/Expense-Tracker.git
cd Expense-Tracker
```

2. Backend Setup
```bash
cd backend
mvn clean install
```

3. Frontend Setup
```bash
cd frontend
npm install
npm start
```

4. Database Setup
- Ensure PostgreSQL is running
- Create a database named 'minisplitwise'
- Update database configurations in `application.properties`

### Docker Deployment

1. Build and run using Docker Compose
```bash
docker-compose up -d
```

This will start:
- Frontend container on port 3000
- Backend container on port 8081
- PostgreSQL container

### Kubernetes Deployment

1. Apply the Kubernetes manifests
```bash
kubectl apply -f k8s/
```

This will create:
- Persistent Volume Claims for database and logs
- Database Deployment and Service
- Frontend Deployment and Service
- Backend Deployment and Service
- Horizontal Pod Autoscaler configurations

## API Documentation

### User Management
- `POST /auth/register` - Register new user
- `POST /auth/authenticate` - User login
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `DELETE /users/{id}` - Delete user

### Personal Bill Management
- `POST /personalBills/addBills` - Add new bill
- `GET /personalBills/getBillById/{id}` - Get bill by ID
- `GET /personalBills/getBillByUser/{email}` - Get user's bills
- `DELETE /personalBills/deleteBillById/{id}` - Delete specific bill
- `DELETE /personalBills/deleteBillByUser/{email}` - Delete all user bills
- `PUT /personalBills/UpdateBillById/{id}` - Update bill
- `GET /personalBills/getTotalExpense/{email}` - Get user's total expenses

## CI/CD Pipeline

The project uses Jenkins for continuous integration and deployment:
1. Code pull from GitHub
2. Build and test using Maven
3. Docker image creation
4. Publication to DockerHub
5. Deployment using Ansible

## Monitoring

- ELK Stack integration for log monitoring
- Log4j2 for application logging
- Real-time application insights through Kibana dashboard

## Docker Images

Available on DockerHub:
- Backend: `pink27/backend:latest`
- Frontend: `pink27/frontend:latest`

## Future Scope

- Integration of multi-user bill splitting
- Graph algorithms for bill simplification
- Microservices architecture implementation
- External payment service integration
- OCR for automated bill data extraction
- Notification service integration


## License

This project is licensed under the MIT License.
