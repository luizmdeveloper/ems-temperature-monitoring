package br.com.luizmariodev.ems.temperature.monitoring.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "alerts")
@Data
public class SensorAlert {

    @Id
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "value",
            column = @Column(name = "id", columnDefinition = "bigint"))
    private SensorId id;
    private Double maxTemperature;
    private Double minTemperature;

    public boolean verifyTemperatureMaxGreaterThan(Double temperature) {
        return this.maxTemperature != null && temperature.compareTo(this.maxTemperature) >= 0;
    }

    public boolean verifyTemperatureMinLessThan(Double temperature) {
        return this.minTemperature != null && temperature.compareTo(this.minTemperature) <= 0;
    }
}
