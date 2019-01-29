package UrlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class UrlShortenController {

    private UrlShortenService shortenerService;

    public UrlShortenController(@Autowired UrlShortenService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping(value = "shortener")
    public ResponseEntity<String> shortenUrl(@RequestParam(name = "url") String originalUrl,
                                             HttpServletRequest request) {
        // TODO: Urls are not validated.
        // TODO: Urls without protocols (http/https) indicated does not redirect properly.
        try {
            String shortenedUrl = request.getRequestURL().toString()
                    + "/r/"
                    + shortenerService.shorten(originalUrl).getId();

            return new ResponseEntity<>(shortenedUrl,
                                        HttpStatus.CREATED);
        } catch (ConflictingIdException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "shortener/{id}")
    public ResponseEntity<UrlStore> getUrl(@PathVariable String id) {
        try {
            return new ResponseEntity<>(shortenerService.getUrl(id), HttpStatus.FOUND);
        } catch (NoSuchUrlStoreException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "shortener/r/{id}")
    public RedirectView redirect(@PathVariable String id) throws NoSuchUrlStoreException {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(shortenerService.getUrl(id).getOriginalUrl());
        return redirectView;
    }

}