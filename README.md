# ioc-demo

A minimal Spring Boot demonstration of Inversion of Control (IoC) and conditional bean registration using `@ConditionalOnProperty`.

This project shows how Spring injects an implementation of a `PaymentService` interface into a `Store` component and how to switch concrete implementations (Paypal or Stripe) using an application property.

Project layout (key files)

- `src/main/java/org/example/iocdemo/IocDemoApplication.java` - Spring Boot entry point.
- `src/main/java/org/example/iocdemo/Store.java` - A component that depends on `PaymentService` and performs a checkout.
- `src/main/java/org/example/iocdemo/controller/StoreController.java` - A simple REST controller exposing a checkout endpoint.
- `src/main/java/org/example/iocdemo/service/PaymentService.java` - The service interface.
- `src/main/java/org/example/iocdemo/service/PaypalService.java` - Paypal implementation annotated with `@ConditionalOnProperty(name="app.payment.provider", havingValue="paypal")`.
- `src/main/java/org/example/iocdemo/service/StripeService.java` - Stripe implementation annotated with `@ConditionalOnProperty(name="app.payment.provider", havingValue="stripe")`.
- `src/main/resources/application.properties` - Default application properties (contains `app.payment.provider=paypal`).

## Overview

This is intentionally small and focused. The key idea is:

- `Store` depends on the `PaymentService` interface.
- Concrete implementations are Spring `@Service` beans that are conditionally loaded depending on the `app.payment.provider` property.
- The REST controller `StoreController` exposes a POST endpoint to trigger a checkout.

Running the application

## Requirements:
- JDK 11+ (or whatever your `pom.xml` specifies).
- macOS zsh (commands below assume zsh, but they work on other shells too).

From the project root, you can run with the bundled Maven wrapper:

```bash
# Run using Spring Boot's plugin
cd /Users/shumkar/Documents/Programming/JAVA/projects/ioc-demo
./mvnw spring-boot:run
```

Alternatively build a jar and run it:

```bash
cd /Users/shumkar/Documents/Programming/JAVA/projects/ioc-demo
./mvnw package
java -jar target/ioc-demo-*.jar
```

Switching payment providers

The default `src/main/resources/application.properties` contains:

```
spring.application.name=ioc-demo

app.payment.provider=paypal
```

To switch to Stripe, you have three straightforward options:

- Edit `src/main/resources/application.properties` and set `app.payment.provider=stripe`.
- Pass the property on the command line when starting with the Maven plugin:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--app.payment.provider=stripe"
```

- Or pass it as a JVM/system property when running the jar:

```bash
java -jar target/ioc-demo-*.jar --app.payment.provider=stripe
```

## HTTP API

The app exposes a single endpoint:

- POST /api/store/checkout?amount=<value>

Example using curl (macOS zsh):

```bash
curl -X POST "http://localhost:8080/api/store/checkout?amount=12.34"
```

### Expected response:

"Checkout completed for amount: $12.34"

You'll see console output from the application describing which payment provider handled the request, e.g.:

```
Checking out with amount: $12.34
Processing payment of $12.34 through PayPal.
```

or for Stripe:

```
Checking out with amount: $12.34
Processing payment of $12.34 through Stripe.
```

## Tests

This project uses JUnit 5 and Mockito for unit tests.

Run tests with:

```bash
cd /Users/shumkar/Documents/Programming/JAVA/projects/ioc-demo
./mvnw test
```


