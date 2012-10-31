package wecui.obfuscation;

/**
 * Method name obfuscation, used whenever we use reflection. 
 * This lets us keep reflected obfuscated methods in a central
 * class to ease updating.
 * 
 * @author yetanotherx
 * 
 * @obfuscated 1.4.2
 */
public enum FieldObfuscation {

    /**
     * NetworkManager instance in NetClientHandler.class
     */
    NETWORKMANAGER("g"),
    /**
     * Packet stream list in NetworkManager.class
     */
    PACKETLIST("p"),
    /**
     * ID->Class hashmap for packets in Packet.class
     */
    IDSTOCLASSES("l"),
    /**
     * Class->ID hashmap for packets in Packet.class
     */
    CLASSESTOIDS("a");
    protected String variable;

    private FieldObfuscation(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }

    public static String getVariable(FieldObfuscation type) {
        return type.getVariable();
    }
}
