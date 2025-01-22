package Types;

/**
 * Stores album name, album author, and a list of songs.
 *
 * @author Nathan Johnson
 * @version 8/23/2024
 */
public class Album {
    private String name;
    private Artist author;
    private Song[] songs;

    /**
     * Constructor for objects of class Album
     *
     * @param  n  Album name
     * @param  a  Artist
     * @param  s  List of songs (Song[])
     */
    public Album(String n, Artist a, Song[] s) {
        this.name = n;
        this.author = a;
        this.songs = s;
    }
    /**
     * @return    name of the album
     */
    public String getName() {
        return this.name;
    }
    /**
     * @param  index  index of the desired song
     * @return        the song at that index
     */
    public Song getSong(int index) {
        return this.songs[index];
    }

    /**
     *
     * @return    Artist attached to the album
     */
    public Artist getAuthor() {
        return this.author;
    }
    /**
     *
     * @return    All songs as array
     */
    public Song[] getSongs() {return this.songs;}

    /**
     * Replaces all songs on this album with a new tracklist.
     *
     * @param  newSongs  Tracklist to use
     */
    public void setSongs(Song[] newSongs) {
        this.songs = newSongs.clone(); // replaces tracklist
    }

    /**
     * Adds a song to the end of the tracklist.
     *
     * @param  newSong  Song to add
     */
    public void addSong(Song newSong) {
        Song[] newSongs = new Song[songs.length + 1]; // new tracklist with one more slot
        for (int i = 0; i < songs.length; i++) {
            newSongs[i] = songs[i]; // clone current tracklist
        }
        newSongs[newSongs.length - 1] = newSong; // add new song to end
        setSongs(newSongs);
    }

    /**
     * Identifies the song, from this album, via an Id.
     *
     * @param  id  The Id (Song name and artist) of the song
     * @return     The song with a matching Id
     */
    public Song identifySong(Id id) {
        Song song = null;

        for (Song s : songs) {
            if (s.getId().equals(id)) {
                song = s;
            }
        }
        return song;
    }

    /**
     * Removes a song from the tracklist.
     *
     * @param  song  Song to remove
     */
    public void removeSong(Song song) {
        Song[] newSongs = new Song[songs.length - 1];
        int blarg = 0; // couldn't think of a better name

        for (int i = 0; i < songs.length; i++) { // clone tracklist of this.songs without removed song
            Song s = songs[i];
            if (s.getId().equals(song.getId())) {
                blarg = 1; // blarg used to not skip over the removed slot
            }
            else {
                newSongs[i - blarg] = s; // blarg is 0 until removed song is passed
            }
        }
        setSongs(newSongs);
    }
    public void print() {
        System.out.println("Album Name: " + this.name);
        System.out.println("Artist Name: " + this.author.toString());
        System.out.println("Track List: ");
        for (Song s : songs) {
            System.out.println(s.getName() + " " + s.getRuntimeAsString());
        }
    }
}
