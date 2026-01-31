package pkg.util;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.Map;
import rbxm.pkg.util.PackageInfo;


public class TypeTokens {

    public static final Type registry = new TypeToken<Map<String, Map<String, PackageInfo>>>() {
    }.getType();

    // Private constructor so no instantiation
    private TypeTokens() {
    }
}