package _a.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    AVAILABLE("Available"),
    PENDING("Pending"),
    SOLD("Sold");

    private final String value;
}
