package Types;

public class SRPair {
    private String string;
    private int runtime;
    public SRPair(String str, int runtimeSeconds) {
        this.string = str;
        this.runtime = runtimeSeconds;
    }
    public SRPair() {
        this.string = "";
        this.runtime = 0;
    }
    public int getRuntime() {
        return runtime;
    }
    public String getName() {
        return string;
    }
    public void setName(String name) {
        this.string = name;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public Song toSong(Id id) {
        return new Song(id, this.runtime);
    }
}
