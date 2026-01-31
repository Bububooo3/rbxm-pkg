package pkg.cmds;

import java.lang.reflect.Method;

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
    private Method fxn;

    public Command(Method f, String... a) {
        this.setArgs(a);
        this.fxn = f;
    }

    @Override
    public void execute(String[] a) {
        this.fxn(a);
        return;
    }
}