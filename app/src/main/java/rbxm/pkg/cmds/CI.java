package rbxm.pkg.cmds;

import java.util.HashMap;

public interface CI {
    HashMap<Integer, String> args = new HashMap<>();
    
    default void setArgs(String... arguments) {
        for (int i=0; i<arguments.length; i++) {
            args.put(i, arguments[i]);
        }
        return;
    }

    default int length() {
        return args.size();
    }

    default void execute(String[] a) {
        System.out.println("This command has not yet been defined.");
        return;
    }
}