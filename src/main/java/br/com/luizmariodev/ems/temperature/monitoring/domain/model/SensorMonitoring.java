package br.com.luizmariodev.ems.temperature.monitoring.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "sensors")
@Data
public class SensorMonitoring {

    @Id
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BIGINT"))
    private SensorId id;

    private BigDecimal lastTemparature;
    private OffsetDateTime updateat;
    private boolean enabled;
}
