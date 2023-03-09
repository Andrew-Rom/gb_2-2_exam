public class Toy {

    private final int TOYID;
    private final String TOYNAME;
    private int victoryFrequency;

    public Toy(int TOYID, String TOYNAME, int victoryFrequency) {
        this.TOYID = TOYID;
        this.TOYNAME = TOYNAME;
        this.victoryFrequency = victoryFrequency;
    }

    public int getTOYID() {
        return TOYID;
    }

    public String getTOYNAME() {
        return TOYNAME;
    }

    public int getVictoryFrequency() {
        return victoryFrequency;
    }
}
