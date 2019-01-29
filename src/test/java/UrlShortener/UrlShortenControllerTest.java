package UrlShortener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlShortenControllerTest {

    public static final String ORIGINAL_URL = "https://www.google.com/";
    private static final String MOCK_ID = "id123";
    public static final String BASEURL = "baseurl";

    private static UrlShortenService mockUrlShortenService;
    private static UrlShortenController urlShortenController;


    @Before
    public void setup() throws ConflictingIdException, NoSuchUrlStoreException {
        UrlStore mockUrlStore = mock(UrlStore.class);
        when(mockUrlStore.getId()).thenReturn(MOCK_ID);
        when(mockUrlStore.getOriginalUrl()).thenReturn(ORIGINAL_URL);

        mockUrlShortenService = mock(UrlShortenService.class);
        when(mockUrlShortenService.shorten(anyString())).thenReturn(mockUrlStore);
        when(mockUrlShortenService.getUrl(matches(MOCK_ID))).thenReturn(mockUrlStore);

        urlShortenController = new UrlShortenController(mockUrlShortenService);
    }


    @Test
    public void shortenUrl() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(BASEURL);
        Mockito.when(httpServletRequest.getRequestURL()).thenReturn(stringBuffer);

        ResponseEntity<String> responseEntity = urlShortenController.shortenUrl(
                ORIGINAL_URL,
                httpServletRequest);

        Assert.assertTrue(responseEntity.hasBody());
        Assert.assertTrue(responseEntity.getBody().startsWith(BASEURL + "/r/"));
    }

    @Test
    public void redirect() throws NoSuchUrlStoreException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(BASEURL);
        Mockito.when(httpServletRequest.getRequestURL()).thenReturn(stringBuffer);

        ResponseEntity<String> responseEntity = urlShortenController.shortenUrl(
                ORIGINAL_URL,
                httpServletRequest);

        RedirectView redirectView = urlShortenController.redirect(MOCK_ID);

        Assert.assertEquals(ORIGINAL_URL, redirectView.getUrl());
    }
}
