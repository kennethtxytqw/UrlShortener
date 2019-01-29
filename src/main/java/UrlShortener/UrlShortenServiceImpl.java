package UrlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenServiceImpl implements UrlShortenService {

    @Autowired
    private UrlStoreService urlStoreService;

    public UrlStore shorten(String originalUrl) throws ConflictingIdException {
        String id = generateId(originalUrl);
        UrlStore urlStore = new UrlStoreImpl(id, originalUrl);
        urlStore = urlStoreService.save(urlStore);

        return urlStore;
    }

    @Override
    public UrlStore getUrl(String id) throws NoSuchUrlStoreException {
        if (urlStoreService.hasUrlStore(id)) {
            return urlStoreService.get(id);
        } else {
            throw new NoSuchUrlStoreException(id);
        }
    }

    private String generateId(String originalUrl) {
        return String.valueOf(originalUrl.hashCode());
    }
}
