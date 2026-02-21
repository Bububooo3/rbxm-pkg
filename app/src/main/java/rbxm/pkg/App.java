package rbxm.pkg;

import rbxm.pkg.cmds.Commands;

public class App {
    public static void main(String[] args) {
        try {
            Commands.run(args);
        } catch (Exception e) {
            System.out.println("There was an issue processing the command");
            System.out.println(e);
        }
    }
}
