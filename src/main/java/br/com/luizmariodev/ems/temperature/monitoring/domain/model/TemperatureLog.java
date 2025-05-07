package br.com.luizmariodev.ems.temperature.monitoring.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "logs")
@Data
public class TemperatureLog {

    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "uuid"))
    private TemperatureLogId id;

    private BigDecimal value;
    private OffsetDateTime registeredAt;

    @AttributeOverride(name = "value", column = @Column(name = "sensor_id", columnDefinition = "bigint"))
    private SensorId sensorId;

}
