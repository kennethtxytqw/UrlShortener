package UrlShortener;

public interface UrlShortenService {
    UrlStore shorten(String originalUrl) throws ConflictingIdException;
    UrlStore getUrl(String id) throws NoSuchUrlStoreException;
}
