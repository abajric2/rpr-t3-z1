package ba.unsa.etf.rpr;

/**
 * Izuzetak za klasu TelefonskiBroj
 * Naslijedjen iz RuntimeException
 */
public class TelefonskiBrojIzuzetak extends RuntimeException {
    public TelefonskiBrojIzuzetak(String izuzetak) {
        super(izuzetak);
    }
}
