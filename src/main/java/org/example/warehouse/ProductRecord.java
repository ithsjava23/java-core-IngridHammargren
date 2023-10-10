package org.example.warehouse;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record ProductRecord(UUID id, String productName, Category category, BigDecimal price) {


    public UUID uuid() {
        return this.id;
    }

    public BigDecimal price() {
        return price;
    }

    public Category category() {
        return category;
    }









}
