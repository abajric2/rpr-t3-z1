package ba.unsa.etf.rpr;

/**
 * Pobrojani tip za gradove
 */
public enum Grad {
    BIHAC("037"),
    GORAZDE("038"),
    LIVNO("034"),
    MOSTAR("036"),
    ORASJE("031"),
    SARAJEVO("033"),
    SIROKI_BRIJEG("039"),
    TRAVNIK("030"),
    TUZLA("035"),
    ZENICA("032"),
    BRCKO("049");
    private final String pozivniBroj;

    public String getPozivni_broj() {
        return pozivniBroj;
    }

    private Grad(String pozivni_broj) {
        this.pozivniBroj = pozivni_broj;
    }

    @Override
    public String toString() {
        return pozivniBroj;
    }
    public static Grad gradIzPozivnog (String pozivniBroj) {
        for (Grad g : Grad.values()) {
            if(g.getPozivni_broj().equals(pozivniBroj)) return g;
        }
        return null;
    }
}
