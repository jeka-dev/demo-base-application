package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

public class FoojayDiscoClient {

    static List<String> getDistros() {
        String res = webResource().path("distributions")
                .queryParam("include_versions", "false")
                .queryParam("include_synonyms", "false")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        JsonObject root = (JsonObject) JsonParser.parseString(res);
        JsonArray distros = root.getAsJsonArray("result");
        return distros.asList().stream()
                .map(el -> el.getAsJsonObject().get("api_parameter").getAsString())
                .toList();
    }

    static List<String> getVersions(String distro) {
        String res = webResource().path("distributions").path(distro)
                .queryParam("include_ea", "false")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        JsonObject root = (JsonObject) JsonParser.parseString(res);
        JsonArray versions = root.getAsJsonArray("result").get(0).getAsJsonObject().get("versions").getAsJsonArray();
        return versions.asList().stream()
                .map(JsonElement::getAsString)
                .toList();
    }

    private static WebResource webResource() {
        final ClientConfig clientConfig = new DefaultClientConfig();
        final Client client = Client.create(clientConfig);
        return client.resource(UriBuilder.fromUri("https://api.foojay.io/disco/v3.0").build());
    }

 }
