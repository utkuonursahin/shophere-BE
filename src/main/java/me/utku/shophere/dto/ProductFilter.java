package me.utku.shophere.dto;

import me.utku.shophere.enums.ProductCategory;

import java.util.List;

public record ProductFilter(String name, List<String> categories ) {
}
