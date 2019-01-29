package UrlShortener;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UrlStoreServiceImpl implements UrlStoreService {

    private static Map<String, UrlStore> mockDB = new HashMap<>();
    // probably should use a proper db here if data meant to be persistent

    @Override
    public UrlStore get(String id) {
        return mockDB.get(id);
    }

    @Override
    public boolean hasUrlStore(String id) {
        return mockDB.containsKey(id);
    }

    @Override
    public UrlStore save(UrlStore urlStore) throws ConflictingIdException {
        if (!mockDB.containsKey(urlStore.getId())) {
            mockDB.put(urlStore.getId(), urlStore);
            return urlStore;
        } else if (mockDB.get(urlStore.getId())
                         .getOriginalUrl()
                         .equals(urlStore.getOriginalUrl())) {
            return mockDB.get(urlStore.getId());
        } else {
            throw new ConflictingIdException(urlStore, mockDB.get(urlStore.getId()));
        }
    }
}
