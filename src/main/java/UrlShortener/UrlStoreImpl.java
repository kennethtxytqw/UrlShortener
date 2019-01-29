package UrlShortener;

public class UrlStoreImpl implements UrlStore {
    private String originalUrl;
    private String id;

    public UrlStoreImpl(String id, String originalUrl) {
        this.originalUrl = originalUrl;
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getOriginalUrl() {
        return originalUrl;
    }
}
