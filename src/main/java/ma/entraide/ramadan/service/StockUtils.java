package ma.entraide.ramadan.service;

public class StockUtils {

    /**
     * Calcule le pourcentage d'avancement par rapport au stock final.
     *
     * @param stockActuel Quantité actuelle en stock.
     * @param stockFinal  Quantité finale attendue.
     * @return Pourcentage d'avancement.
     */
    public static double calculerPourcentage(int stockActuel, int stockFinal) {
        if (stockFinal == 0) {
            throw new IllegalArgumentException("Le stock final ne peut pas être zéro.");
        }
        return  stockActuel / stockFinal * 100;
    }
}