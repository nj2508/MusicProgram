package Types;

/**
 * Used to identify individual songs.
 *
 * @author Nathan Johnson
 * @version 8/23/2024
 */

public class Id {
    public final Artist author;
    public final String songname;

    /**
     * Constructor for objects of class Id
     *
     * @param  a  Artist's name
     * @param  s  Song name
     */
    public Id(Artist a, String s) {
        this.author = a;
        this.songname = s;
    }
}
