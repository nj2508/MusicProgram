import Types.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runtime stuff goes on here
 *
 * @author Nathan Johnson
 * @version 9/13/2024
 */
public class Main {
    private static ArrayList<Album> albums = new ArrayList<>(); // list of all albums on file
    private static Scanner in = new Scanner(System.in);
    /**
     * Call readAlbums, take user input, print desired album
     */
    public static void main(String[] args) throws IOException {
        albums = readAlbums(); // initialize albums on startup
        System.out.println("What album would you like to listen to?");
        String albumName = in.next();
        for (Album album : albums) {
            String str = cullLineBreaks(album.getName()); // remove '\n' from end
            System.out.println(str.equalsIgnoreCase(albumName));
            if (str.equalsIgnoreCase(albumName)) {
                System.out.println("Match detected");
                album.print();
            }
        }
    }

    /**
     * Reads albums from albums.txt on startup.
     *
     * @return    ArrayList of albums to load on startup
     */
    public static ArrayList<Album> readAlbums() throws IOException {
        ArrayList<Album> albums = new ArrayList<>();
        ArrayList<Song> finalSongs = new ArrayList<>();

        String albumName = "";
        String artistName = "";
        ArrayList<SRPair> songs = new ArrayList<>();
        int i;

        String currentWord = "";
        ArrayList<Character> chars = new ArrayList<>();
        FileReader fr = new FileReader("C:\\Users\\Natha\\IdeaProjects\\MusicProgram\\src\\Storage\\albums.txt");
        while ((i = fr.read()) != -1) {
            chars.add((char) i);
        }
        for (int k = 0; k < chars.size(); k++) {
            char ch = chars.get(k);
            if (ch != ' ' && ch != '\n' && ch != '\r') {
                currentWord += ch;
            } else {
                if (currentWord.equals("ALBUM:")) {
                    albumName = parseLine(chars, k);
                    currentWord = "";
                }
                if (currentWord.equals("ARTIST:")) {
                    artistName = parseLine(chars, k);
                    currentWord = "";
                }
                if (currentWord.equals("SONGS:")) {
                    songs = parseSongs(chars, k);
                    currentWord = "";
                }
                if (currentWord.equals("END")) {
                    Artist artist = new Artist(artistName);
                    for (SRPair srp : songs) {
                        finalSongs.add(srp.toSong(new Id(artist, srp.getName())));
                    }
                    Song[] songArr = finalSongs.toArray(new Song[0]);
                    Album dummyAlbum = new Album(albumName, new Artist(artistName), songArr);

                    albums.add(dummyAlbum);
                    for (Song s : dummyAlbum.getSongs()) {
                        s.setAlbum(dummyAlbum);
                    }
                    finalSongs.clear();
                    songs.clear();
                }
                currentWord = "";
            }
        }

        return albums;
    }

    /**
     * Reads one line from a txt file
     *
     * @param chars arrayList of chars from line
     * @param indexToStart index to start at in chars
     * @return line as string
     */
    public static String parseLine(ArrayList<Character> chars, int indexToStart) {
        char ch;
        int i = indexToStart;
        String line = "";

        while ((ch = chars.get(i + 1)) != '\n' && i < chars.size()) {
            line += ch;
            i++;
        }
        return line;
    }

    /**
     * Reads all songs after "SONGS:" in txt file and until "END"
     *
     * @param chars arrayList of all chars in txt file
     * @param indexToStart index to start at in chars
     * @return arrayList of SRPairs which can be decoupled into song titles and runtimes
     */
    public static ArrayList<SRPair> parseSongs(ArrayList<Character> chars, int indexToStart) {
        ArrayList<SRPair> songsList = new ArrayList<>();

        char ch;
        char ch1;
        boolean flip = false; // false = song title, true = runtime
        String songTitle = "";
        String runTime = "";
        String line = "";

        for (int i = indexToStart; i < chars.size(); i++) {
            ch = chars.get(i);
            if (ch != '\n' && ch != '\r') {
                line += ch;
            } else if (!line.equals("END")) {
                for (int k = 0; k < line.length(); k++) {
                    ch1 = line.toCharArray()[k];
                    if (!flip) { // title
                        if (ch1 != '(') { // title until (
                            songTitle += ch1;
                        } else {
                            flip = true;
                        }
                    } else {
                        if (ch1 != ')' && ch1 != '(' && ch1 != '\n' && ch1 != '\r') {
                            runTime += ch1;
                        } else {
                            flip = false;
                        }
                    }
                }
                flip = false;
                if (!songTitle.equals("") && !runTime.equals("")) {
                    songsList.add(new SRPair(songTitle, parseRuntime(runTime)));
                }
                line = "";
                runTime = "";
                songTitle = "";
            } else {
                 i = chars.size();
            }
        }
        return songsList;
    }

    /**
     * Converts string (minute:second) time into seconds
     *
     * @param runTime string (minute:second) format
     * @return integer seconds
     */
    public static int parseRuntime(String runTime) {
        int rt = 0;

        String minutes = ""; // minutes as string
        String seconds = ""; // seconds as string
        boolean colonPassed = false; // false = minutes | true = second

        for (char c : runTime.toCharArray()) { // read runTime
            if (c == ':') {
                colonPassed = true; // swaps between minutes and seconds
            } else {
                if (!colonPassed) { // minutes
                    minutes = minutes + c;
                } else { // seconds
                    seconds = seconds + c;
                }
            }
        }
        try {
            rt = Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds); // convert to seconds
        } catch (NumberFormatException e) {
            System.out.println("Error parsing runtime");
        }
        return rt;
    }

    /**
     * Removes linebreaks from a phrase
     *
     * @param phrase phrase to check
     * @return phrase without linebreaks
     */
    public static String cullLineBreaks(String phrase) {
        char[] chars = phrase.toCharArray();
        ArrayList<Character> finalChars = new ArrayList<>();
        String finalPhrase = "";

        for (char c : chars) {
            System.out.println(c);
            if (c == '\n' || c == '\r') {
                System.out.println("Linebreak detected!");
            } else {
                finalChars.add(c);
            }
        }

        for (char c : finalChars) {
            finalPhrase += c;
        }
        return finalPhrase;
    }
}