package pkg.util;

import java.util.ArrayList;

public final class Constants {
    public static final String REGURL = "https://bububooo3.github.io/rbxm-pkg/registry.json"; // Registry url

    /**
     * @apiNote Allowed # args for each command (including cmd itself so at least 1)
     */
    public class CommandArgs {
        public static final ArrayList<Integer> INSTALL = new ArrayList<>(List.of(2, 3));
        public static final ArrayList<Integer> HELP = new ArrayList<>(List.of(-1));

    }

    private Constants() {
    }
}