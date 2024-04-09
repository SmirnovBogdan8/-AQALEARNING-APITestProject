package org.example.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ReqResMock {

    public static void run() {
        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        stubFor(get(urlPathEqualTo("/api/users"))
                .withQueryParam("page", equalTo("2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("LISTUSERS.json")
                )
        );

        stubFor(post(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("CREATE.json")
                )
        );
    }

    public static void main(String[] args) {
        run();
    }
}
