/**
 * 
 */
package net.lahwran.fevents;

/**
 * Event superclass. should be extended as: 
 * <pre>
 *     class MyEvent extends Event<MyEvent> {
 *         public static final HandlerList<MyEvent> handlers = new HandlerList<MyEvent>();
 *
 *         @Override
 *         HandlerList<MyEvent> getHandlers() {
 *             return handlers;
 *         }
 *         @Override
 *         void call(Listener<MyEvent> listener) {
 *             listener.onEvent(this);
 *         }
 *     }
 * </pre>
 * @author lahwran
 * @param <TEvent> Event class
 *
 */
public abstract class Event<TEvent extends Event<TEvent>> {

    /**
     * Stores cancelled status. will be false unless a subclass publishes
     * setCancelled.
     */
    protected boolean cancelled = false;

    /**
     * Get the static handler list of this event subclass.
     * 
     * @return HandlerList to call event with
     */
    protected abstract HandlerList<TEvent> getHandlers();

    /**
     * Get event type name.
     * 
     * @return event name
     */
    protected abstract String getEventName();

    public String toString() {
        return getEventName()+" ("+this.getClass().getName()+")";
    }

    /**
     * Set cancelled status. Events which wish to be cancellable should
     * implement Cancellable and implement setCancelled as:
     * <pre>
     *     public void setCancelled(boolean cancelled) {
     *         super.setCancelled(cancelled);
     *     }
     * </pre>
     * @param cancelled True to cancel event
     */
    protected void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returning true will prevent calling any even Order slots. 
     * 
     * @see Order
     * @return false if the event is propogating; events which do not implement
     *                Cancellable should never return true here
     */
    public boolean isCancelled() {
        return cancelled;
    }
}
