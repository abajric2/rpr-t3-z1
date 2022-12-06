package ba.unsa.etf.rpr;

import java.util.*;

public class Imenik {

    private Map<String, TelefonskiBroj> brojevi;

    public Imenik() {
        brojevi=new HashMap<>();
    }
    public void dodaj(String ime, TelefonskiBroj broj) {

        brojevi.put(ime, broj);
    }

    public String dajBroj(String ime) {
        TelefonskiBroj broj = brojevi.get(ime);
        if(broj != null) return broj.ispisi();
        else return null;
    }

    public String dajIme(TelefonskiBroj broj) {
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(entry.getValue().ispisi().equals(broj.ispisi())) return entry.getKey();
        }
        return null;
    }
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
    private boolean daLiJeIzGrada (TelefonskiBroj broj, Grad g) {
        return broj instanceof TelefonskiBroj && ((FiksniBroj)broj).getGrad().equals(g);
    }
    public Set<String> izGrada(Grad g) {
        Set<String> osobe = new TreeSet<>();
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(daLiJeIzGrada(entry.getValue(), g)) {
                osobe.add(entry.getKey());
            }
        }
        return osobe;
    }
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
