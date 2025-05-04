package com.example.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConnectController {
    @Autowired
    private RestClient restClient;

    @GetMapping("/health")
    public String health() {
        return "Health";
    }

    @GetMapping("/token")
    public String getToken() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "ms-client");
        formData.add("client_secret", "7KI4jGnt31wFP3eHnzbELdLCtKrewpxx");
        formData.add("grant_type", "client_credentials");

        TokenResponse response = restClient.post()
                .uri("http://localhost:9090/realms/ms-realm/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(TokenResponse.class);

        return response.getAccessToken();
    }
}
