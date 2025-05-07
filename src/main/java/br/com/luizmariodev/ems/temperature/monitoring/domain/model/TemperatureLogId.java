package br.com.luizmariodev.ems.temperature.monitoring.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemperatureLogId {

    private UUID value;

    public TemperatureLogId(UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public TemperatureLogId(String value) {
        Objects.requireNonNull(value);
        this.value = UUID.fromString(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
