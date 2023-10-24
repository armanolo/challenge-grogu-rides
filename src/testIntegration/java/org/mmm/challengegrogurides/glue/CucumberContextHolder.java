package org.mmm.challengegrogurides.glue;

import org.springframework.stereotype.Component;
import io.restassured.response.Response;

@Component
public class CucumberContextHolder {
    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
