package UrlShortener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    public static final String ORIGINAL_URL = "https://www.google.com/";

    @Test
    public void shortensAndRedirect() throws Exception {
        MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/shortener")
                                                                                   .param("url",
                                                                                          ORIGINAL_URL))
                                                                  .andExpect(status().isCreated())
                                                                  .andReturn()
                                                                  .getResponse();

        String shortenedUrl = httpServletResponse.getContentAsString();

        String mapping = "/shortener/r/";
        String id = shortenedUrl.substring(shortenedUrl.indexOf(mapping) + mapping.length());

        // Check if it redirects to the ORIGNAL_URL
        this.mockMvc.perform(get("/shortener/r/" + id))
                                          .andExpect(redirectedUrl(ORIGINAL_URL));
    }

}
