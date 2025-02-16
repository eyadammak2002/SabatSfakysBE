package tn.SabatSfakys.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TailleConverter implements AttributeConverter<Taille, String> {

    @Override
    public String convertToDatabaseColumn(Taille taille) {
        return (taille == null) ? null : taille.getValeur().toString();
    }

    @Override
    public Taille convertToEntityAttribute(String valeur) {
        if (valeur == null) {
            return null;
        }
        try {
            // Vérifier si la valeur est un entier (pointure)
            int pointure = Integer.parseInt(valeur);
            return Taille.fromValeur(pointure);
        } catch (NumberFormatException e) {
            // Sinon, c'est une taille standard (S, M, L, XL)
            return Taille.fromValeur(valeur);
        }
    }
}
