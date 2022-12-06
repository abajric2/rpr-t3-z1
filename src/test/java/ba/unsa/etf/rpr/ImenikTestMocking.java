package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ImenikTestMocking {
    private static Imenik imenik = new Imenik();
    @BeforeAll
    public static void dodajPodatke() {
        imenik.dodaj("Meho", new FiksniBroj(Grad.SARAJEVO, "123-123"));
        imenik.dodaj("Haso", new MobilniBroj(62, "568-324"));
        imenik.dodaj("Sejo", new MedunarodniBroj("+387","60/123-4567"));
        imenik.dodaj("Mujo", new MobilniBroj(60, "456-4567"));
    }
    @Test
    public void MockTest() {
        Map<String, TelefonskiBroj> mapa = Mockito.mock(Map.class);
        Mockito.when(mapa.get("Meho")).thenReturn(new FiksniBroj(Grad.ZENICA, "333-333"));
        imenik.setBrojevi(mapa);
        TelefonskiBroj fiksniBroj = new FiksniBroj(Grad.SARAJEVO, "123-123");
        assertNotEquals(imenik.dajIme(fiksniBroj), "Meho");
    }

}
