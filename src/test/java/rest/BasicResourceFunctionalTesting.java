package rest;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import rest.api.Uris;
import rest.RestBuilder;
import rest.api.Wrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestsApiConfig.class})
public class BasicResourceFunctionalTesting {

    private static final String URL_API = "http://localhost:8080/springProject.0.0.1-SNAPSHOT" + Uris.SERVLET_MAP;

    @Test
    public void testState() {
        URI uri = UriComponentsBuilder.fromHttpUrl(URL_API + Uris.BASICS + Uris.STATE).build().encode().toUri();
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        String response = responseEntity.getBody();

        System.out.println("Response: " + response);
        assertEquals("{\"response\":\"OKKKKKK " + Uris.VERSION + "\"}", response);
    }
    
    @Test
    public void testErrorDivision() {
        try {
            new RestBuilder<Wrapper>(URL_API).path(Uris.BASICS).path(Uris.CALC).param("dividendo", "1").param("divisor", "0").get().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, httpError.getStatusCode());
            System.out.println("ERROR >>>>> " + httpError.getMessage());
            System.out.println("ERROR >>>>> " + httpError.getResponseBodyAsString());
        }
    }

}
