# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "com.griotold.springboot_sns.SpringbootSnsApplicationTests"

# Run a single test method
./gradlew test --tests "com.griotold.springboot_sns.SpringbootSnsApplicationTests.contextLoads"

# Clean build
./gradlew clean build
```

## Project Overview

This is a Spring Boot 4.0.1 SNS (Social Networking Service) application using:
- Java 25
- Spring Data JPA for persistence
- Spring Web MVC for REST APIs
- H2 (in-memory, for development) and PostgreSQL (for production) databases

## Architecture

Base package: `com.griotold.springboot_sns`

Standard Spring Boot layered architecture is expected:
- `controller/` - REST API endpoints
- `service/` - Business logic
- `repository/` - Data access layer (Spring Data JPA)
- `domain/` or `entity/` - JPA entities
- `dto/` - Data transfer objects
