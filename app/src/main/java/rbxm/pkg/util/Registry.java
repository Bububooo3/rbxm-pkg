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
         * Definition for a Registry
         */
        private class blueprint {
                public Map<String, Map<String, PackageInfo>> pkgs;
        }

        private static blueprint local_registry; // our registry

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
                Type type = new TypeToken<Map<String, Map<String, PackageInfo>>>() {
                }.getType();

                Map<String, Map<String, PackageInfo>> raw = gson.fromJson(response.body(), type);

                local_registry = new blueprint();
                local_registry.pkgs = raw;

                System.out.println("Accessed registry: [" + Constants.REGURL + "]");
        }

        public static blueprint getRegistry() {
                return local_reg;
        }
}