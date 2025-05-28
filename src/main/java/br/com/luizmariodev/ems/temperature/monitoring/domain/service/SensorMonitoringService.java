package br.com.luizmariodev.ems.temperature.monitoring.domain.service;

import br.com.luizmariodev.ems.temperature.monitoring.api.model.SensorMonitoringOutput;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorMonitoring;
import br.com.luizmariodev.ems.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class SensorMonitoringService {

    private final SensorMonitoringRepository repository;

    public SensorMonitoringService(SensorMonitoringRepository repository) {
        this.repository = repository;
    }

    public SensorMonitoringOutput detail(SensorId id) {
        var optionalSensor = repository.findById(id);
        return optionalSensor.map(this::convertEntityToOutput).orElseGet(() -> createResponseDefault(id));
    }

    public void enable(SensorId sensorId) {
        var optionalSensor = repository.findById(sensorId);

        optionalSensor.ifPresent(sensor -> {
            sensor.setEnabled(true);
            repository.saveAndFlush(sensor);
        });
    }

    public void disable(SensorId sensorId) {
        var optionalSensor = repository.findById(sensorId);

        optionalSensor.ifPresent(sensor -> {
            sensor.setEnabled(false);
            repository.saveAndFlush(sensor);
        });
    }

    public Optional<SensorMonitoringOutput> findBySensorId(TSID id) {
        var optionalSensor = repository.findById(new SensorId(id));

        if (!optionalSensor.isEmpty()) {
            return Optional.of(convertEntityToOutput(optionalSensor.get()));
        }

        return Optional.empty();
    }

    public void update(TSID sensorId, Double value, OffsetDateTime lastDate) {
        var optionalSensor = repository.findById(new SensorId(sensorId));
        optionalSensor.ifPresent(sensor -> {
            if (sensor.isEnabled()) {
                sensor.setLastTemparature(BigDecimal.valueOf(value));
                sensor.setUpdateat(lastDate);
                repository.save(sensor);
            }
        });
    }

    private SensorMonitoringOutput convertEntityToOutput(SensorMonitoring sensorMonitoring) {
        var sensorMonitoringOutput = new SensorMonitoringOutput();
        sensorMonitoringOutput.setId(sensorMonitoring.getId().getValue());
        sensorMonitoringOutput.setUpdateAt(sensorMonitoring.getUpdateat());
        sensorMonitoringOutput.setLastTemparature(sensorMonitoring.getLastTemparature());
        sensorMonitoringOutput.setEnabled(sensorMonitoringOutput.isEnabled());
        return sensorMonitoringOutput;
    }

    private SensorMonitoringOutput createResponseDefault(SensorId sensorId) {
        var sensorMonitoringOutput = new SensorMonitoringOutput();
        sensorMonitoringOutput.setId(sensorId.getValue());
        sensorMonitoringOutput.setUpdateAt(null);
        sensorMonitoringOutput.setLastTemparature(null);
        sensorMonitoringOutput.setEnabled(false);
        return sensorMonitoringOutput;
    }
}
