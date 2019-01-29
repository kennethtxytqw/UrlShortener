# UrlShortener

## Local development requirements
- java development kit 8
    - For linux, `sudo apt-get install openjdk-8-jdk`

## Running locally
1. `git clone` this repo
2. `cd` into the project base directory.
3. Run `./gradlew run`.
4. Access `localhost:8080/swagger-ui.html` with your preferred web browser.

## Usage
- `{baseurl}/swagger-ui.html` has the api documentation.
- `{baseurl}/shortener` - POST
    - take in the original url via the url parameter
    - returns the shortened url

## Improvements to be made
1. Authentication with api-key.
2. Integrate a database for persistent storage.
3. More unit tests to be written.