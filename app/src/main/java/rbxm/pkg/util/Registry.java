package pkg.util;

import java.util.Map;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import rbxm.pkg.util.Constants;
import rbxm.pkg.util.TypeTokens;

public static class Registry {
        /**
         * Record definition for a Registry
         */
        public record RegistryRecord(Map<String, Map<String, PackageInfo>> pkgs) {
        }

        private TypeTokens.registry local_reg; // our registry

        /**
         * Initialize the registry
         * 
         * @throws Exception
         */
        public static void initRegistry() throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(Constants.REGURL))
                                .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                        System.out.println("Failed to load registry [HTTP " + response.statusCode() + "]");
                        System.exit(1);
                }

                Gson gson = new Gson();

                RegistryType raw = gson.fromJson(response.body(), type);

                registry = new Registry(raw);
                System.out.println("Accessed registry: [" + Constants.REGURL + "]");
        }

        public static RegistryType getRegistry() {
                return registry;
        }
}