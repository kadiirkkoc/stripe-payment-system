# Stripe Payment Service in Spring Boot (Gradle-based)
This project is a Stripe payment service implemented in Spring Boot, using Gradle as the build tool. It provides a straightforward way to integrate Stripe payments into your web application.

- Table of Contents
- Getting Started
- Prerequisites
- Installation
- Usage
- Configuration
- Stripe API Key
- Webhook Configuration
- Endpoints
- Creating a Payment Intent
- Handling Webhooks
- Contributing
- Getting Started
- Prerequisites
Before you begin, ensure you have met the following requirements:

## Java 8 or higher installed.
Stripe account - You'll need to sign up for a Stripe account and obtain your API keys.

## Installation
- Clone the repository:
```
git clone https://github.com/kadiirkkoc/stripe-payment-system.git
```
- Navigate to the project directory:
```
cd stripe-payment-service
```
- Build the project using Gradle:
```
./gradlew build
```
- Run the application:
```
./gradlew bootRun
```

## Usage
Once the application is up and running, you can start integrating it with your web application to handle Stripe payments. See the Endpoints section for details on available API endpoints.

## Configuration
Stripe API Key
To configure your Stripe API key, open the application.properties file and set your Stripe secret key:
```
stripe.api.key=your_stripe_secret_key
```
