package _a.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    AVAILABLE("Available"),
    PENDING("Pending"),
    SOLD("Sold");

    private final String value;
}
