package br.com.luizmariodev.ems.temperature.monitoring.domain.service;

import br.com.luizmariodev.ems.temperature.monitoring.api.model.SensorMonitoringOutput;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorMonitoring;
import br.com.luizmariodev.ems.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorMonitoringService {

    private SensorMonitoringRepository repository;

    public SensorMonitoringService(SensorMonitoringRepository repository) {
        this.repository = repository;
    }

    public SensorMonitoringOutput detail(SensorId id) {
        var optionalSensor = repository.findById(id);

        if (optionalSensor.isPresent()) {
            return convertEntityToOutput(optionalSensor.get());
        }

        return createResponseDefault(id);
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
}
