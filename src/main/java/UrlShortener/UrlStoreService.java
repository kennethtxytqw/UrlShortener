package UrlShortener;

public interface UrlStoreService {
    UrlStore get(String id);
    boolean hasUrlStore(String id);
    UrlStore save(UrlStore urlStore) throws ConflictingIdException;
}
