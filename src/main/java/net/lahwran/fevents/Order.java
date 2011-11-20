package net.lahwran.fevents;

/**
 * Order of event listener calls.
 * 
 * Odd-numbered slots are called even when
 * events are marked "not propogating". If an event stops propogating partway
 * through an even slot, that slot will not cease execution, but future even
 * slots will not be called.
 * 
 * @author lahwran
 */
public enum Order {
    /**
     * Called before all other handlers.
     * Should be used for high-priority event canceling.
     */
    Earlist(0),

    /**
     * Called after "Earliest" handlers and before "Early" handlers. Is called
     * even when event has been canceled. Should generally be used to uncancel
     * events canceled in Earliest.
     */
    Early_IgnoreCancelled(1),

    /**
     * Called after "Earliest" handlers. Should generally be used for low
     * priority event canceling.
     */
    Early(2),

    /**
     * Called after "Early" handlers and before "Default" handlers. Is called
     * even when event has been canceled. This is for general-purpose
     * always-run events.
     */
    Default_IgnoreCancelled(3),
    /**
     * Default call, for general purpose handlers
     */
    Default(4),

    /**
     * Called after "Default" handlers and before "Late" handlers. Is called
     * even when event has been canceled.
     */
    Late_IgnoreCancelled(5),

    /**
     * Called after "Default" handlers. 
     */
    Late(6),

    /**
     * Called after "Late" handlers and before "Latest" handlers. Is called
     * even when event has been canceled.
     */
    Latest_IgnoreCancelled(7),

    /**
     * Called after "Late" handlers.
     */
    Latest(8),

    /**
     * Called after "Latest" handlers. No changes to the event should be made
     * in this order slot (though it is not enforced). Is called even when
     * event has been cancelled.
     */
    Monitor(9);

    private int index;

    Order(int index) {
        this.index = index;
    }
 
    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }
}