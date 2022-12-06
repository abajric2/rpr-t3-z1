package ba.unsa.etf.rpr;

import java.util.*;

/**
 * Klasa za rad sa telefonskim brojevima, radi sa klasama izvedenim iz apstraktne klase Telefonskibroj
 */
public class Imenik {

    private Map<String, TelefonskiBroj> brojevi;

    public Imenik() {
        brojevi=new HashMap<>();
    }

    /**
     * Metoda koja dodaje novi broj u imenik
     * @param ime - ime oosbe
     * @param broj - telefonski broj osobe
     */
    public void dodaj(String ime, TelefonskiBroj broj) {

        brojevi.put(ime, broj);
    }

    /**
     * Vraca telefonski broj osobe cije ime je dato kao parametar
     * @param ime - ime osobe ciji broj zelimo
     * @return telefonski broj
     */
    public String dajBroj(String ime) {
        TelefonskiBroj broj = brojevi.get(ime);
        if(broj != null) return broj.ispisi();
        else return null;
    }

    /**
     * Vraca ime osobe ciji broj je parametar
     * @param broj - broj osobe cije ime zelimo
     * @return - ime osobe
     */
    public String dajIme(TelefonskiBroj broj) {
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(entry.getValue().ispisi().equals(broj.ispisi())) return entry.getKey();
        }
        return null;
    }

    /**
     * Vraca imena svih osoba cije ime pocinje slovom koje je dato kao parametar
     * @param s
     * @return
     */
    public String naSlovo(char s) {
        StringBuilder osobe = new StringBuilder();
        int brojac = 1;
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(entry.getKey().startsWith(String.valueOf(s)))  {  // Provjeravamo da li ime pocinje slovom koje je poslano kao parametar
                osobe.append(brojac)
                        .append(". ")
                        .append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue().ispisi())
                        .append(System.lineSeparator());
                brojac = brojac + 1;
            }
        }
        return osobe.toString();
    }

    /**
     * Pomocna funkcija koja vraca tacno ili netacno, u ovisnosti da li broj koji je dat kao parametar
     * pripada gradu koji je drugi parametar
     * @param broj
     * @param g
     * @return
     */
    private boolean daLiJeIzGrada (TelefonskiBroj broj, Grad g) {
        return broj instanceof TelefonskiBroj && ((FiksniBroj)broj).getGrad().equals(g);
    }

    /**
     * Vraca imena svih osoba iz grada datog kao parametar
     * @param g
     * @return
     */
    public Set<String> izGrada(Grad g) {
        Set<String> osobe = new TreeSet<>();
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(daLiJeIzGrada(entry.getValue(), g)) {
                osobe.add(entry.getKey());
            }
        }
        return osobe;
    }

    /**
     * Vraca telefonske brojeve svih osoba iz grada datog kao parametar
     * @param g
     * @return
     */
    public Set<TelefonskiBroj> izGradaBrojevi(Grad g) {
        Set<TelefonskiBroj> skupBrojeva = new TreeSet<>(new Comparator<TelefonskiBroj>() {
            @Override
            public int compare(TelefonskiBroj o1, TelefonskiBroj o2) {
                return o1.ispisi().compareTo(o2.ispisi());
            }
        });
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(daLiJeIzGrada(entry.getValue(), g)) {
                skupBrojeva.add(entry.getValue());
            }
        }
        return skupBrojeva;
    }




}
