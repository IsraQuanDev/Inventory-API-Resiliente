package com.inventory.dto; 

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String name,
        String description,
        @NotBlank String category,
        @NotNull @Positive BigDecimal price,
        @NotNull @PositiveOrZero Integer stock
) {}