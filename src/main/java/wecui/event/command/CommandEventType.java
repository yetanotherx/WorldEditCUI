package wecui.event.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum for command events. Stores the event class and any aliases.
 * 
 * @author yetanotherx
 * 
 */
public enum CommandEventType {
    
    VERSION(VersionCommandEvent.class, "ver");
    
    protected final Class<? extends CommandEventBase> eventClass;
    protected List<String> aliases;

    private CommandEventType(Class<? extends CommandEventBase> eventClass) {
        this.eventClass = eventClass;
        this.aliases = new ArrayList<String>();
    }

    private CommandEventType(Class<? extends CommandEventBase> eventClass, String ... aliases) {
        this.eventClass = eventClass;
        this.aliases = Arrays.asList(aliases);
    }

    public Class<? extends CommandEventBase> getEventClass() {
        return eventClass;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public static CommandEventType getTypeFromCommand(String key) {
        for (CommandEventType value : CommandEventType.values()) {
            if (value.name().toLowerCase().equals(key) || value.getAliases().contains(key) ) {
                return value;
            }
        }
        return null;
    }
    
}
