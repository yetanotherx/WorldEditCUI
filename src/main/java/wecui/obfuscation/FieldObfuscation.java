package wecui.obfuscation;

import com.mumfrey.liteloader.util.ModUtilities;

/**
 * Method name obfuscation, used whenever we use reflection. 
 * This lets us keep reflected obfuscated methods in a central
 * class to ease updating.
 * 
 * @author yetanotherx
 * @author Mumfrey
 * 
 * @obfuscated 1.5.2
 */
public enum FieldObfuscation {

    /**
     * Packet stream list in NetworkManager.class
     */
    dataPackets("q", "field_74487_p"),
    /**
     * ID->Class hashmap for packets in Packet.class
     */
    packetIdToClassMap("l", "field_73294_l");

    protected String variable;
    
    protected String seargeVariable;

    private FieldObfuscation(String variable, String seargeVariable) {
        this.variable = variable;
        this.seargeVariable = seargeVariable;
    }

    public String getVariable() {
        return ModUtilities.getObfuscatedFieldName(this.toString(), this.variable, this.seargeVariable);
    }

    public static String getVariable(FieldObfuscation type) {
        return ModUtilities.getObfuscatedFieldName(type.toString(), type.variable, type.seargeVariable);
    }
}
