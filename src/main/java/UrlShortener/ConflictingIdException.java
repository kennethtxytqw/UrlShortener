package UrlShortener;

public class ConflictingIdException extends Exception {
    public ConflictingIdException(UrlStore urlStore1, UrlStore urlStore2) {
        super(String.format("{%s} and {%s} has the same id {%s}.",
                            urlStore1.getOriginalUrl(),
                            urlStore2.getOriginalUrl(),
                            urlStore1.getId()));
    }
}
