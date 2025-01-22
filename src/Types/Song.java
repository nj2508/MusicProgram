package Types;

/**
 * Stores an Id (Song name and artist), the album it's on, and its runtime.
 *
 * @author Nathan Johnson
 * @version 8/23/2024
 */
public class Song {
    private Id id;
    private Album album;
    private int runtime;

    /**
     * Constructor for objects of class Song which are not singles
     *
     * @param  i  Song Id (Name and artist)
     * @param  a  Album the song is on
     * @param  r  Runtime of the song (in seconds)
     */
    public Song(Id i, Album a, int r) {
        this.id = i;
        this.album = a;
        this.runtime = r;
    }

    /** Constructor for objects of class Song which are singles or do not have an album yet
     *
     * @param  i  Song Id (Name and artist)
     * @param  r  Runtime of the song (in seconds)
     */
    public Song (Id i, int r) {
        this.id = i;
        this.album = null;
        this.runtime = r;
    }
    public void parseRuntimeFromString(String s) {
        int runtime = 0;

        String minutes = ""; // minutes as string
        String seconds = ""; // seconds as string
        boolean colonPassed = false;

        for (char c : s.toCharArray()) { // read s
            if (c == ':') {
                colonPassed = true; // swaps between minutes and seconds
            }
            else {
                if (!colonPassed) { // minutes
                    minutes = minutes + c;
                } else { // seconds
                    seconds = seconds + c;
                }
            }
        }
        runtime = Integer.valueOf(minutes) * 60 + Integer.valueOf(seconds); // convert to seconds
        this.runtime = runtime;
    }
    public String convertRuntimeToString() {
        int minutes = runtime / 60;
        int seconds = runtime % 60;
        if (seconds < 10) {
            return minutes + ":0" + seconds;
        } else {
            return minutes + ":" + seconds;
        }
    }
    public Id getId() {return this.id;}
    public String getName() {return this.id.songname;}
    public Artist getAuthor() {return this.id.author;}
    public void setAlbum(Album a) {this.album = a;
    }
    public String getRuntimeAsString() {return this.convertRuntimeToString();}
    public int getRuntime() {return this.runtime;}
}