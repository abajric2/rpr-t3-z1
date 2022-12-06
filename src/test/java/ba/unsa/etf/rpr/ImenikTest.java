package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImenikTest
{
    private static Imenik imenik = new Imenik();
    @BeforeAll
    public static void dodajPodatke() {
        imenik.dodaj("Meho", new FiksniBroj(Grad.SARAJEVO, "123-123"));
        imenik.dodaj("Haso", new MobilniBroj(62, "568-324"));
        imenik.dodaj("Sejo", new MedunarodniBroj("+387","60/123-4567"));
        imenik.dodaj("Mujo", new MobilniBroj(60, "456-4567"));
    }
    @Test
    public void dajBrojPronadjeno() {
        String broj = imenik.dajBroj("Meho");
        assertEquals(broj, "033/123-123");
    }
    @Test
    public void dajImeNijePronadjeno() {
        String broj = imenik.dajBroj("asdf");
        assertNull(broj);
    }
    @Test
    public void naSlovo() {
        String osobe = imenik.naSlovo('M');
        assertEquals(osobe, "1. Mujo - 060/456-4567" + System.lineSeparator() + "2. Meho - 033/123-123" + System.lineSeparator());
    }
    @Test
    public void naSlovoPrazanString() {
        String osobe = imenik.naSlovo('A');
        assertEquals(osobe, "");
    }
}
