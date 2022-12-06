package ba.unsa.etf.rpr;

import java.util.Scanner;
import java.util.Set;

/**
 * Klasa koja koristi metode klase Imenik
 * Korisnik unosi neku od ponudjenih opcija te se na osnovu toga izvrsavaju odredjene funkcije
 */
public class Program {
    public static Scanner ulaz = new Scanner(System.in);
    public static Imenik imenik = new Imenik();
    public static void main(String[] args) {
        while(true) {
        System.out.println("Unesite naredbu (dodaj, dajBroj, dajIme, naSlovo, izGrada, izGradaBrojevi, izlaz)");
        String naredba = ulaz.nextLine();
            switch (naredba) {
                case "dodaj":
                    dodaj();
                    break;
                case "dajBroj":
                    dajBroj();
                    break;
                case "dajIme":
                    dajIme();
                    break;
                case "naSlovo":
                    naSlovo();
                    break;
                case "izGrada":
                    izGrada();
                    break;
                case "izGradaBrojevi":
                    izGradaBrojevi();
                    break;
                case "izlaz":
                    System.exit(0);
                default:
                    System.out.println("Pogresna naredba");
            }
        }
    }
    private static TelefonskiBroj unesiBrojTelefona() {
        System.out.println("Unesite tip telefonskog broja (mobilni, fiksni, medjunarodni):");
        String tip = ulaz.nextLine();
        switch(tip) {
            case "mobilni":
                System.out.println("Unesite mobilnu mrezu:");
                int mreza = ulaz.nextInt();
                System.out.println("Unesite broj: ");
                String broj = ulaz.nextLine();
                return new MobilniBroj(mreza, broj);
            case "fiksni":
                System.out.println("Unesite pozivni broj:");
                String pozivniBroj = ulaz.nextLine();
                System.out.println("Unesite broj: ");
                String fiksniBroj = ulaz.nextLine();
                return new FiksniBroj(Grad.gradIzPozivnog(pozivniBroj), fiksniBroj);
            case "medjunarodni":
                System.out.println("Unesite pozivni broj drzave: ");
                String pozivniBrojDrzave = ulaz.nextLine();
                System.out.println("Unesite broj: ");
                String medjunarodniBroj = ulaz.nextLine();
                return new MedunarodniBroj(pozivniBrojDrzave, medjunarodniBroj);
        }
        return null;
    }
    private static void dodaj() {
        System.out.println("Unesite ime osobe: ");
        String ime = ulaz.nextLine();
        System.out.println("Unesite broj osobe: ");
        TelefonskiBroj broj = unesiBrojTelefona();
        imenik.dodaj(ime, broj);
    }
    private static void dajBroj() {
        System.out.println("Unesite ime osobe ciji broj zelite ispisati:");
        String ime = ulaz.nextLine();
        String broj = imenik.dajBroj(ime);
        if(broj != null) System.out.println(broj);
        else System.out.println("U imeniku ne postoji osoba sa unesenim imenom");
    }
    private static void dajIme() {
        System.out.println("Unesite broj osobe cije ime zelite ispisati:");
        TelefonskiBroj broj = unesiBrojTelefona();
        String ime = imenik.dajIme(broj);
        if(ime != null) System.out.println(ime);
        else System.out.println("U imeniku ne postoji osoba sa unesenim brojem");
    }
    private static void naSlovo() {
        System.out.println("Unesite slovo na koje zelite pretraziti imena:");
        char s = ulaz.nextLine().toCharArray()[0];
        System.out.println(imenik.naSlovo(s));
    }
    private static void izGrada() {
        System.out.println("Unesite ime grada:");
        String grad = ulaz.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            System.out.println(imenik.izGrada(g));
        }
        catch(Exception izuzetak) {
            System.out.println("Pogresan grad");
        }
    }
    private static void izGradaBrojevi() {
        System.out.println("Unesite ime grada:");
        String grad = ulaz.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<TelefonskiBroj> skupBrojeva = imenik.izGradaBrojevi(g);
            for(TelefonskiBroj br : skupBrojeva) {
                System.out.println(br.ispisi());
            }
        }
        catch(Exception izuzetak) {
            System.out.println("Pogresan grad");
        }
    }
}
