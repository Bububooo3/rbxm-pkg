package rbxm.pkg.cmds;

import java.util.function.Consumer;

/**
 * <h3>Command Class</h3>
 * <hr>
 * The template used to define and run commands
 * <hr>
 * 
 * @apiNote Implements {@code CI} interface
 * 
 * @see CI
 */
public class Command implements CI {
    private Consumer<String[]> fxn;

    public Command(Consumer<String[]> f, String... a) {
        this.setArgs(a);
        this.fxn = f;
    }

    @Override
    public void execute(String[] a) {
        this.fxn.accept(a); // sigh (✿◡‿◡)

        // try {
        // this.fxn.invoke(null, (Object) a);
        // } catch (IllegalAccessException | InvocationTargetException e) {
        // e.printStackTrace();
        // }
        return;
    }
}