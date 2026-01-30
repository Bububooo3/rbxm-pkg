package rbxm.pkg;

import java.util.Map;

public class Registry {
    // packages.get("name").get("version")
    public Map<String, Map<String, PackageInfo>> packages;
}
