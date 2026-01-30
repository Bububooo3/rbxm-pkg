package rbxm.pkg;

import java.util.List;

public record PackageInfo(
    String url,
    String description,
    List<String> dependencies
) {}
