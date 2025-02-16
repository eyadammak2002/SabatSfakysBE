package tn.SabatSfakys.model;

public enum Taille {
    
    // Pointures de chaussures
    POINTURE_36(36),
    POINTURE_37(37),
    POINTURE_38(38),
    POINTURE_39(39),
    POINTURE_40(40),
    POINTURE_41(41),
    POINTURE_42(42),
    POINTURE_43(43),
    POINTURE_44(44),
    POINTURE_45(45),

    // Tailles standards de vêtements
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");

    private final Object valeur; // Peut être un entier (pointure) ou une chaîne (taille vêtement)

    Taille(int valeur) { // Constructeur pour les pointures
        this.valeur = valeur;
    }

    Taille(String valeur) { // Constructeur pour les tailles de vêtements
        this.valeur = valeur;
    }

    public Object getValeur() {
        return valeur;
    }

    public static Taille fromValeur(Object valeur) {
        for (Taille t : Taille.values()) {
            if (t.getValeur().equals(valeur)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Valeur de taille invalide : " + valeur);
    }
}
