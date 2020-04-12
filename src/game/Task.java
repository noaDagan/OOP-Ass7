package game;

/**
 * The interface.
 *
 * @param <T> a generic
 */
public interface Task<T> {
    /**
     * run method.
     * @return a T
     */
    T run();
}
