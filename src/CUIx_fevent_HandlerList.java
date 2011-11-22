
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map.Entry;

/**
 * @author lahwran
 * @param <TEvent> Event type
 *
 */
@SuppressWarnings("unchecked")
public class CUIx_fevent_HandlerList<TEvent extends CUIx_fevent_Event<TEvent>> {

    /**
     * handler array. this field being an array is the key to this system's speed.
     * 
     * is initialized in bake().
     */
    public CUIx_fevent_Listener<TEvent>[][] handlers;
    /**
     * Int array same length as handlers. each value in this array is the index
     * of an Order slot, corossponding to the equivalent value in handlers.
     * 
     * is initialized in bake().
     */
    public int[] handlerids;
    /**
     * Dynamic handler lists. These are changed using register() and
     * unregister() and are automatically baked to the handlers array any
     * time they have changed.
     */
    private final EnumMap<CUIx_fevent_Order, ArrayList<CUIx_fevent_Listener<TEvent>>> handlerslots;
    /**
     * Whether the current handlerslist has been fully baked. When this is set
     * to false, the Map<Order, List<Listener>> will be baked to Listener[][]
     * next time the event is called.
     * 
     * @see EventManager.callEvent
     */
    private boolean baked = false;
    /**
     * List of all handlerlists which have been created, for use in bakeall()
     */
    private static ArrayList<CUIx_fevent_HandlerList> alllists = new ArrayList<CUIx_fevent_HandlerList>();

    /**
     * Bake all handler lists. Best used just after all normal event
     * registration is complete, ie just after all plugins are loaded if
     * you're using fevents in a plugin system.
     */
    public static void bakeall() {
        for (CUIx_fevent_HandlerList h : alllists) {
            h.bake();
        }
    }

    /**
     * Create a new handler list and initialize using EventManager.Order
     * handlerlist is then added to meta-list for use in bakeall()
     */
    public CUIx_fevent_HandlerList() {
        handlerslots = new EnumMap<CUIx_fevent_Order, ArrayList<CUIx_fevent_Listener<TEvent>>>(CUIx_fevent_Order.class);
        for (CUIx_fevent_Order o : CUIx_fevent_Order.values()) {
            handlerslots.put(o, new ArrayList<CUIx_fevent_Listener<TEvent>>());
        }
        alllists.add(this);
    }

    /**
     * Register a new listener in this handler list
     * @param listener listener to register
     * @param order order location at which to call provided listener
     */
    public void register(CUIx_fevent_Listener<TEvent> listener, CUIx_fevent_Order order) {
        if (handlerslots.get(order).contains(listener)) {
            throw new IllegalStateException("This listener is already registered to order " + order.toString());
        }
        baked = false;
        handlerslots.get(order).add(listener);
    }

    /**
     * Remove a listener from all order slots
     * @param listener listener to purge
     */
    public void unregister(CUIx_fevent_Listener<TEvent> listener) {
        for (CUIx_fevent_Order o : CUIx_fevent_Order.values()) {
            unregister(listener, o);
        }
    }

    /**
     * Remove a listener from a specific order slot
     * @param listener listener to remove
     * @param order order from which to remove listener
     */
    public void unregister(CUIx_fevent_Listener<TEvent> listener, CUIx_fevent_Order order) {
        if (handlerslots.get(order).contains(listener)) {
            baked = false;
            handlerslots.get(order).remove(listener);
        }
    }

    /**
     * Bake HashMap and ArrayLists to 2d array - does nothing if not necessary
     */
    void bake() {
        if (baked) {
            return; // don't re-bake when still valid
        }
        ArrayList<CUIx_fevent_Listener[]> handlerslist = new ArrayList<CUIx_fevent_Listener[]>();
        ArrayList<Integer> handleridslist = new ArrayList<Integer>();
        for (Entry<CUIx_fevent_Order, ArrayList<CUIx_fevent_Listener<TEvent>>> entry : handlerslots.entrySet()) {
            CUIx_fevent_Order orderslot = entry.getKey();

            ArrayList<CUIx_fevent_Listener<TEvent>> list = entry.getValue();

            int ord = orderslot.getIndex();
            handlerslist.add(list.toArray(new CUIx_fevent_Listener[list.size()]));
            handleridslist.add(ord);
        }
        handlers = handlerslist.toArray(new CUIx_fevent_Listener[handlerslist.size()][]);
        handlerids = new int[handleridslist.size()];
        for (int i = 0; i < handleridslist.size(); i++) {
            handlerids[i] = handleridslist.get(i);
        }
        baked = true;
    }
}
