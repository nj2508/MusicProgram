package Types;

/**
 * Simple storage class for a string Name
 *
 * @author Nathan Johnson
 * @version 8/23/2024
 */
public class Artist {
    private String name;

    /**
     * Constructor for objects of class Artist.
     *
     * @param  n  Artist's name
     */
    public Artist(String n) {
        this.name = n;
    }

    /**
     * @return    Artist's name
     */
    public String toString() {
        return this.name;
    }
}