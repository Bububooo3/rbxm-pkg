package pkg.util;

import java.util.List;

public record PackageInfo(
        String url,
        String description,
        List<String> dependencies) {
}
