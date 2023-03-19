# StackExchange demo

## Build

JDK 11 and Node 14 required.

- `./gradlew clean bootJar`

With Docker:

- `cd middleware; docker build -t stackexchange-demo .`

## Run

Local

- `./middleware/build/libs/middleware-1.0.jar`
- Open [this link](http://localhost:9101) in browser

Docker

- `docker run -p PORT:9101 stackexchange-demo` (replace `PORT` to any unused port)
- Open `http://localhost:PORT` in browser
