package pkg.cmds;

import rbxm.pkg.util.Constants.CommandArgs;

import java.nio.file.Path;
import java.util.ArrayList;
import rbxm.pkg.util.Registry;

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
        if (!verifyArgs(a.length, CommandArgs.INSTALL)) {
            improper();
            return;
        }

        var reg = Registry.getRegistry();
        var pkgs = reg.pkgs;

        final String name = a[1];

        // Library exists?
        if (!pkgs.containsKey(name)) {
            System.out.println("Invalid package: pkg-" + name);
            return;
        }

        // Version exists?
        var versions = pkgs.get(name);
        String version = (a.length == 2) ? versions.get("latest") : a[2]; // 2 means no version written

        if (!versions.containsKey(version)) {
            System.out.println("Invalid version: v" + version);

            version = versions.get("latest");

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
        Files.createDirectories(installPath); // just makes a folder there

        System.out.println("Installation Complete ( " + installPath.toAbsolutePath() + " )"); // for the users to know
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
        if (!verifyArgs(a.length, CommandArgs.HELP)) {
            improper();
            return;
        }

        empty();
        return;
    }

    /**
     * <h3>Publish Command</h3>
     * <hr>
     * Run to upload your package to the registry
     * 
     * <hr>
     * {@code rbxm-pkg publish [pkg-url] [pkg-name] [pkg-version] [pkg-author] [pkg-details] [pkg-dependencies]}
     * <hr>
     * 
     * @param a            is the array of arguments. It contains the items below.
     * @param name         is the pkg to install
     * @param version      is the version of the pkg
     * @param author       is the developer of the pkg
     * @param details      is a description of the pkg
     * @param dependencies is the other pkgs the pkg relies on
     * 
     * @throws Exception
     */
    public static void publish(String[] a) throws Exception {
        if (!verifyArgs(a.length, CommandArgs.PUBLISH)) {
            improper();
            return;
        }

        /*
         * Check if pkg w/ that name already exists
         * Make an entry to the online registry (probably have to push to github...)
         */

        empty();
        return;
    }

    /**
     * <h3>Details Command</h3>
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
    public static void details(String[] a) throws Exception {
        if (!verifyArgs(a.length, CommandArgs.DETAILS)) {
            improper();
            return;
        }

        final String name = a[1];
        String version = (a.length == 2) ? "latest" : a[2];

        if (!(verifyPkg(name, version))) {
            System.out.println("Failed to get details of pkg-" + name + "@v"+version);
            return;
        }

        // Install it then
        PackageInfo info = versions.get(version);

        empty();
        return;
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
    private static void empty(String... a) throws Exception {
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
    private static void improper(String... a) throws Exception {
        System.out.println("Please format your request properly: rbxm-pkg [command] [arguments]");
        return;
    }

    /**
     * <h3>Verify Arguments Helper Method</h3>
     * <hr>
     * Check quickly to see if this command was formatted properly.
     * <div>
     * Returns {@code true} if the entry is valid
     * 
     * <hr>
     * {@code not runnable for users}
     * <hr>
     * 
     * @param length  is the length of the array of arguments entered by the user
     * @param allowed is the ArrayList of allowed amounts of arguments
     * 
     * @apiNote {@code -1} means any number of arguments is allowed
     * 
     * @throws Exception
     * 
     * @see CommandArgs
     */
    private static boolean verifyArgs(int length, ArrayList<Integer> allowed) throws Exception {
        return (allowed.contains(a.length) || allowed.contains(-1));
    }

    /**
     * <h3>Verify Package Helper Method</h3>
     * <hr>
     * Check quickly to see if this package and version are accessible.
     * <div>
     * Returns {@code true} if package is accessible
     * 
     * <hr>
     * {@code not runnable for users}
     * <hr>
     * 
     * @param name    is the name of the package
     * @param version is the version of the package
     * 
     * @throws Exception
     * 
     */
    private static boolean verifyPkg(String name, String version) throws Exception {
        var reg = Registry.getRegistry();
        var pkgs = reg.pkgs;

        // Library exists?
        if (!pkgs.containsKey(name)) {
            System.out.println("Invalid package: pkg-" + name);
            return false;
        }

        // Version exists?
        var versions = pkgs.get(name);
        version = (a.length == 2) ? versions.get("latest") : a[2]; // 2 means no version written

        if (!versions.containsKey(version)) {
            System.out.println("Invalid version: v" + version);

            version = versions.get("latest");

            System.out.println("Retrieving latest release: v" + version);

            if (!versions.containsKey(version)) {
                System.out.println("Invalid version: v" + version);
                System.out.println("Installation failed for pkg-" + name);
                return false;
            }
        }

        return true;
    }
}