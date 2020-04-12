package hitevent;

/**
 * The counter interface.
 */
public class Counter {
    //Members
    private int number = 0;

    /**
     * Constructor build the counter by a number.
     *
     * @param number a type int
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * The method add number to current count in accordance.
     *
     * @param num type int
     */
    public void increase(int num) {
        this.number = this.number + num;
    }

    /**
     * The method subtract number from current count.
     *
     * @param num type int
     */
    public void decrease(int num) {
        this.number = this.number - num;
    }

    /**
     * The methods get current count.
     *
     * @return the value of a number
     */
    public int getValue() {
        return this.number;
    }
}