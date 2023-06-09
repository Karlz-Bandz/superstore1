package pl.superstore.superstore.models;

import lombok.Getter;

/**
 * <strong>Category</strong> enum<br>
 * Here are all categories occurring on
 * superstore. This example introduces a
 * kind of hair dressing shop.
 *
 */
@Getter
public enum Category
{
    SHAMPOOS("Szampony"),
    CONDITIONERS("Odżywki"),
    MASKS("Maski"),
    TREATMENTS("Kuracje"),
    OILS("Olejki"),
    PEELINGS("Pilingi"),
    SETS("Zestawy"),
    STYLING("Stylizacja"),
    TURN("Skręt"),
    TEXTURE("Tekstura"),
    DYES("Koloryzacja"),
    OXIDANTS("Oksydanty"),
    WASHABLECOLOR("Koloryzacja zmywalna"),
    BRIGHTENERS("Rozjaśniacze"),
    KERATIN("Prostowanie keratynowe"),
    DURABLE("Trwała");

    private String category;

    Category(String category)
    {
        this.category = category;
    }
}
