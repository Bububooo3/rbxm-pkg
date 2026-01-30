package rbxm.pkg;

import com.google.gson.Gson;
import com.sun.tools.javac.Main;

import java.io.InputStreamReader;
import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;

public class App {
    private static Registry registry;

    public static void main(String[] args) throws Exception {
        loadRegistry();
    }

    private static void loadRegistry() throws Exception {
        Gson gson = new Gson();
        Type type = new TypeToken<Registry>() {
        }.getType();

        try (InputStreamReader reader = new InputStreamReader(
                Main.class.getResourceAsStream("https://bububooo3.github.io/rbxm-pkg/registry.json"))) {
            registry = gson.fromJson(reader, type);
        }
    }
}
