package rbxm.pkg.util;

import java.util.Map;
import java.util.Optional;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Registry {
        /**
         * Definition for a Registry
         */
        public static class blueprint {
                public Map<String, Map<String, PackageInfo>> pkgs;
        }

        public static blueprint local_registry; // our registry

        /**
         * Initialize the registry
         */
        public static void initRegistry() {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(Constants.REGURL))
                                .build();
                try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() != 200) {
                                System.out.println("Failed to load registry [HTTP " + response.statusCode() + "]");
                                System.exit(1);
                        }

                        Gson gson = new Gson();
                        Type type = new TypeToken<Map<String, Map<String, PackageInfo>>>() {
                        }.getType();

                        Map<String, Map<String, PackageInfo>> raw = gson.fromJson(response.body(), type);

                        local_registry = new blueprint();
                        local_registry.pkgs = raw;

                        System.out.println("Accessed registry: [" + Constants.REGURL + "]");
                } catch (Exception e) {
                        System.out.println("There was a problem initializing the package registry");
                        System.out.print("Retry?");
                }

        }

        public static Optional<blueprint> getRegistry() {
                if (local_registry == null) {
                        System.out.println("The package registry was not found");
                        return Optional.empty();
                } else {
                        return Optional.of(local_registry);
                }
        }
}