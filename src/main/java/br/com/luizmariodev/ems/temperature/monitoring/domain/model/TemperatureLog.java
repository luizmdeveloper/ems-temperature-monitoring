package br.com.luizmariodev.ems.temperature.monitoring.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Entity
@Table(name = "logs")
@Data
public class TemperatureLog {

    @Id
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "uuid"))
    private TemperatureLogId id;

    private Double temperature;
    private OffsetDateTime registeredAt;

    @AttributeOverride(name = "value", column = @Column(name = "sensor_id", columnDefinition = "bigint"))
    private SensorId sensorId;

}
