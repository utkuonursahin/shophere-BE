package me.utku.shophere.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductCategory {
    ELECTRONICS("Electronics"),
    BOOKS("Books"),
    CLOTHING("Clothing"),
    FOOD("Food"),
    TOYS("Toys"),
    SPORTS("Sports"),
    BEAUTY("Beauty"),
    HOME("Home"),
    OTHER("Other");

    private final String enumValue;
}
