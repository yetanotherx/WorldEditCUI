package wecui.obfuscation;

/**
 * Method name obfuscation
 * 
 * @author yetanotherx
 * 
 * @obfuscated
 */
public enum MethodObfuscation {

    /**
     * Location of the .minecraft directory
     */
    MINECRAFTDIR("aj"),
    NETWORKMANAGER("g"),
    PACKETLIST("n"),
    IDSTOCLASSES("j"),
    CLASSESTOIDS("a");
    
    protected String variable;

    private MethodObfuscation(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
    
    public static String getVariable(MethodObfuscation type) {
        return type.getVariable();
    }
    
    public String toString() {
        return getVariable();
    }
}
