package pkg.cmds;

import rbxm.pkg.util.Registry;
import rbxm.pkg.util.TypeTokens;

/**
 * @apiNote {@code a[0]} is the command itself
 * @see Command
 * @see CI
 */
public static class executables {
    /**
     * <h3>Install Command</h3>
     * <hr>
     * Run to download a package from the registry to a specified location
     * 
     * <hr>
     * {@code rbxm-pkg install [pkg-name] [pkg-version]}
     * <hr>
     * 
     * @param a       is the array of arguments. It contains the items below.
     * @param name    is the pkg to install
     * @param version is the version of the pkg
     * 
     * @throws Exception
     */
    public static void install(String[] a) throws Exception {
        if (!(a.length == 3)) {
            improper();
            return;
        }

        final String name = a[1];
        final String version = a[2];

        var reg = Registry.getRegistry();
        var pkgs = reg.pkgs();

        // Library exists?
        if (!pkgs.containsKey(name)) {
            System.out.println("Invalid package: pkg-" + name);
            return;
        }

        // Version exists?
        var versions = pkgs.get(name);

        if (!versions.containsKey(version)) {
            System.out.println("Invalid version: v" + version);

            version = pkgs.get("latest");
            
            System.out.println("Retrieving latest release: v" + version);

            if (!versions.containsKey(version)) {
                System.out.println("Invalid version: v" + version);
                System.out.println("Installation failed for pkg-" + name);
                return;
            }
        }

        // Install it then
        PackageInfo info = versions.get(version);

        System.out.println("Installing pkg-" + name + "@v" + version);
        System.out.println("(from " + info.url() + ")");

        Path installPath = Path.of("path/to/rblx/fldr", name, version); // TODO
        Files.createDirectories(installPath);

        System.out.println("Installation Complete ( " + installPath.toAbsolutePath() + " )");
    }

    /**
     * <h3>Help Command</h3>
     * <hr>
     * Run to provide the user with a navigable layout of the commands system
     * 
     * <hr>
     * {@code rbxm-pkg help [category?] [subcategories?]}
     * <hr>
     * 
     * @param a             is the array of arguments. It contains the items below.
     * @param category      the category to explore <b>(optional)</b>
     * @param subcategories are the params that follow category <b>(optional)</b>
     * 
     * @throws Exception
     */
    public static void help(String[] a) throws Exception {

    }

    /**
     * <h3>Empty Command</h3>
     * <hr>
     * A placeholder method to indicate that there's nothing here yet.
     * 
     * <hr>
     * {@code not runnable for users}
     * <hr>
     * 
     * @param a is the array of arguments. There should be none.
     * 
     * @throws Exception
     */
    public static void empty(String... a) throws Exception {
        System.out.println("This command has not yet been implemented.");
        return;
    }

    /**
     * <h3>Improper Command</h3>
     * <hr>
     * You didn't format correctly!
     * Just a simple fallback mechanism. We'll update it later.
     * 
     * <hr>
     * {@code not runnable for users}
     * <hr>
     * 
     * @param a is the array of arguments. There should be none.
     * 
     * @throws Exception
     */
    public static void improper(String... a) throws Exception {
        System.out.println("Please format your request properly: rbxm-pkg [command] [arguments]");
        return;
    }
}