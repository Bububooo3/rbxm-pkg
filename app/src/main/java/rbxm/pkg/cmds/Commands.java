package rbxm.pkg.cmds;

import java.util.Map;

import org.jspecify.annotations.NonNull;

public class Commands {
    /**
     * <h3>Storage Map</h3>
     * <hr>
     * Stores tokens as Strings and their corresponding values as Commands
     * 
     * <hr>
     * {@code storage.get(COMMAND NAME)}
     * {@code COMMAND.execute(ARGUMENTS)}
     * <hr>
     * 
     * @see Command
     */
    private static final Map<String, @NonNull Command> storage = Map.of(
            // Help
            "help", new Command(executables::help, "category?", "subcategories?"),
            "$h", new Command(executables::help, "category?", "subcategories?"),
            // (Probably inefficient to have it twice, idk)

            // Install
            "install", new Command(executables::install, "pkg-name", "pkg-version"),
            "$i", new Command(executables::install, "pkg-name", "pkg-version"),

            // Publish
            "publish",
            new Command(executables::publish, "pkg-url", "pkg-name", "pkg-version", "pkg-author", "pkg-details",
                    "pkg-dependencies"),
            "$p",
            new Command(executables::publish, "pkg-url", "pkg-name", "pkg-version", "pkg-author", "pkg-details",
                    "pkg-dependencies"),

            // Details
            "details", new Command(executables::details, "pkg-name", "pkg-version"),
            "$d", new Command(executables::details, "pkg-name", "pkg-version"));

    /**
     * <h3>Run Method</h3>
     * <hr>
     * Read tokens and run corresponding command
     *
     * <hr>
     * {@code run(COMMAND NAME, ARG1, ARG2, ...)}
     * <hr>
     * 
     * @param args is the array of arguments. It contains the items entered by the
     *             user.
     * 
     */
    public static void run(String[] args) {
        if (args.length == 0) {
            System.out.println("");
            return;
        }

        if (storage.containsKey(args[0])) {
            try {
                
                var cData = storage.get(args[0]);
                cData.execute(args);

            } catch (Exception e) {
                System.out.println("Failed to execute command: " + e.getMessage());
            }
            return;

        } else {
            System.out.println("Failed to locate command \"" + args[0] + "\"");
            return;
        }
    }
}