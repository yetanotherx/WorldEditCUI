package wecui.exception;

/**
 * Special exception that only gets called during initialization
 * Throwing this halts the loading of the mod
 * 
 * @author yetanotherx
 * 
 */
public class InitializationException extends Exception {
    private static final long serialVersionUID = 1L;

    public InitializationException(String string) {
        super(string);
    }

    public InitializationException() {
    }
    
}
