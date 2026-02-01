package rbxm.pkg.util;

import java.util.ArrayList;
import java.util.List;

public final class Constants {
    public static final String REGURL = "https://bububooo3.github.io/rbxm-pkg/registry.json"; // Registry url

    /**
     * @apiNote Allowed # args for each command (including cmd itself so at least 1)
     * @apiNote {@code -a} means any number of args {@code n} is allowed that
     *          fulfills {@code n >= |a + 1|}
     */
    public class CommandArgs {
        /**
         * {@code rbxm-pkg install [pkg-name] [pkg-version]}
         */
        public static final ArrayList<Integer> INSTALL = new ArrayList<>(List.of(2, 3));

        /**
         * {@code rbxm-pkg help [category?] [subcategories?]}
         */
        public static final ArrayList<Integer> HELP = new ArrayList<>(List.of(-1));

        /**
         * {@code rbxm-pkg publish [pkg-url] [pkg-name] [pkg-version] [pkg-author] [pkg-details] [pkg-dependencies?]}
         */
        public static final ArrayList<Integer> PUBLISH = new ArrayList<>(List.of(-6));

        /**
         * {@code rbxm-pkg help [category?] [subcategories?]}
         */
        public static final ArrayList<Integer> DETAILS = new ArrayList<>(List.of(-1));

    }

    private Constants() {
    }
}