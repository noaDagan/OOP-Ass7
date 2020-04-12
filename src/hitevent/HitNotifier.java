package hitevent;

/**
 * The HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * The method add a listener to the list of hit events.
     *
     * @param hl as a listener add to hit events
     */
    void addHitListener(HitListener hl);

    /**
     * The method remove a listener from the list of hit events.
     *
     * @param hl as a listener delete from hit events
     */
    void removeHitListener(HitListener hl);
}