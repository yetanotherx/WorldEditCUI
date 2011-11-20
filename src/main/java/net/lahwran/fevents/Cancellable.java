/**
 * 
 */
package net.lahwran.fevents;

/**
 * @author lahwran
 */
public interface Cancellable {

    /**
     * If an event stops propogating (ie, is cancelled) partway through an even
     * slot, that slot will not cease execution, but future even slots will not
     * be called.
     * 
     * @param cancelled True to set event canceled, False to uncancel event.
     */
    public void setCancelled(boolean cancelled);

    /**
     * Get event canceled state.
     * 
     * @return whether event is cancelled
     */
    public boolean isCancelled();
}
