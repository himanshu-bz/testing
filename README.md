# Selenium Java Login + Add to Cart + Checkout (Demo)

This project provides a Selenium + Java example for a **test/demo ecommerce site** (`saucedemo.com`) that automates:

1. Login
2. Add product to cart
3. Proceed through checkout
4. Verify successful order completion

> This is intentionally built against a demo environment, not a real marketplace account flow.

## Prerequisites

- Java 17+
- Maven 3.9+
- Chrome installed (Selenium Manager will handle driver setup automatically)

## Run tests

```bash
mvn test
```

## Main test file

- `src/test/java/com/example/EcommerceCheckoutTest.java`
