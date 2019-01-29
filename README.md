# UrlShortener

## Running Locally
1. Git pull this repo
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