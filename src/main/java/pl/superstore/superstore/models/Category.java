package pl.superstore.superstore.models;

import java.util.EnumSet;

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
    DURABLE("Trwała"),
    TEST("Test"),
    TEST2("Test2");

    public static final EnumSet<Category> ANOTHER = EnumSet.of(TEST, TEST2);

    public static final EnumSet<Category> HAIRS = EnumSet.of(
            SHAMPOOS,
            CONDITIONERS,
            MASKS,
            TREATMENTS,
            OILS,
            PEELINGS,
            SETS,
            STYLING,
            TURN,
            TEXTURE,
            DYES,
            OXIDANTS,
            WASHABLECOLOR,
            BRIGHTENERS,
            KERATIN,
            DURABLE);


    Category(String category)
    {
    }
}
