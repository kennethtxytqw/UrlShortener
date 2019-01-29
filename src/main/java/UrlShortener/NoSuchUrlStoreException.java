package UrlShortener;

public class NoSuchUrlStoreException extends Exception {
    public NoSuchUrlStoreException(String id) {
        super(String.format("No Url stored under %s.", id));
    }
}
