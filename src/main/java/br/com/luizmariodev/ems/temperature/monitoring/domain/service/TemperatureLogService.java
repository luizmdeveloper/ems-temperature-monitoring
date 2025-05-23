package br.com.luizmariodev.ems.temperature.monitoring.domain.service;

import br.com.luizmariodev.ems.temperature.monitoring.api.model.TemperatureLogOutput;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.repository.TemperatureLogRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemperatureLogService {

    private final TemperatureLogRepository repository;

    public TemperatureLogService(TemperatureLogRepository repository) {
        this.repository = repository;
    }

    public Page<TemperatureLogOutput> findAllBySensor(TSID sensor, Pageable pageable) {
        var logs = repository.findAllBySensorId(new SensorId(sensor), pageable);

        return logs.map(temperatureLog -> {
           TemperatureLogOutput temperatureLogOutput = new TemperatureLogOutput();
           temperatureLogOutput.setSensorId(temperatureLog.getSensorId().getValue());
           temperatureLogOutput.setValue(temperatureLogOutput.getValue());
           temperatureLogOutput.setId(temperatureLog.getId().getValue());
           temperatureLogOutput.setRegisteredAt(temperatureLog.getRegisteredAt());
           return temperatureLogOutput;
        });
    }
}
