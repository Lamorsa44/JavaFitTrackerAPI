package fitnesstracker.entity.fitapp;

import fitnesstracker.exception.BadRequestException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record FitAppRequest(@NotBlank String name,
                            @NotNull String description,
                            @NotNull String category) {
    FitAppCategory parseCategory() {
        return Optional.ofNullable(FitAppCategory.parse(category))
                .orElseThrow(() -> new BadRequestException("Invalid category"));
    }
}
