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
    /**
     * NetworkManager instance in NetClientHandler
     */
    NETWORKMANAGER("g"),
    /**
     * Packet stream in NetworkManager
     */
    PACKETLIST("n"),
    /**
     * ID->Class hashmap for packets
     */
    IDSTOCLASSES("j"),
    /**
     * Class->ID hashmap for packets
     */
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
}
