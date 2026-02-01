package rbxm.pkg.util;

import java.util.List;

public record PackageInfo(
        String url,
        String description,
        String author,
        List<String> dependencies) {
}
