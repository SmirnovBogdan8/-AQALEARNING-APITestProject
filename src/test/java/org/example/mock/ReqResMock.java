package org.example.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ReqResMock {

    public static void run() throws FileNotFoundException {
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

        stubFor(get(urlPathEqualTo("/api/users/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("SINGLEUSER.json")
                )
        );

        stubFor(get(urlPathEqualTo("/api/users/23"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("SINGLEUSERNOTFOUND.json")
                )
        );

        stubFor(get(urlPathEqualTo("/api/unknown"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("LISTRESOURCE.json")
                )
        );


        stubFor(get(urlPathEqualTo("/api/unknown/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("SINGLERESOURCE.json")
                )
        );

        stubFor(get(urlPathEqualTo("/api/unknown/23"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("SINGLERESOURCENOTFOUND.json")
                )
        );

        stubFor(post(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody(generateResponse("src/test/resources/__files/CREATE.json", "POST"))
                )
        );

        stubFor(put(urlEqualTo("/api/users/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(generateResponse("src/test/resources/__files/PUTUPDATE.json", "PUT"))
                )
        );

        stubFor(patch(urlEqualTo("/api/users/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(generateResponse("src/test/resources/__files/PATCHUPDATE.json", "PATCH"))
                )
        );

        stubFor(delete(urlEqualTo("/api/users/2"))
                .willReturn(aResponse()
                        .withStatus(204)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("DELETE.json")
                )
        );

        stubFor(post(urlEqualTo("/api/register"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("REGISTERSUCCESSFUL.json")
                )
        );

        stubFor(post(urlEqualTo("/api/login"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("LOGINSUCCESSFUL.json")
                )
        );

        stubFor(get(urlPathEqualTo("/api/users"))
                .withQueryParam("delay", equalTo("3"))
                .willReturn(aResponse()
                        .withFixedDelay(10000)
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("DELAYEDRESPONSE.json")
                )
        );

        stubFor(get(urlPathEqualTo("/echo"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{request.body}")));
    }

    private static String generateResponse(String path, String method) throws FileNotFoundException {
        JsonObject jsonObject;
        FileReader reader = new FileReader(path);
        JsonParser jsonParser = new JsonParser();
        jsonObject = jsonParser.parse(reader).getAsJsonObject();

        if(method.equals("POST")){
            jsonObject.addProperty("id", generateId());
            jsonObject.addProperty("createdAt", generateDateTime());
        } else {
            jsonObject.addProperty("updatedAt", generateDateTime());
        }



        return jsonObject.toString();
    }

    private static int generateId() {
        Random random = new Random();
        return random.nextInt(990) + 10;
    }

    private static String generateDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime.now().format(formatter);
    }

    public static void main(String[] args) throws FileNotFoundException {
        run();
    }
}
