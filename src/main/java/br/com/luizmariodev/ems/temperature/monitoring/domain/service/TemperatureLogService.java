package br.com.luizmariodev.ems.temperature.monitoring.domain.service;

import br.com.luizmariodev.ems.temperature.monitoring.api.model.TemperatureLogOutput;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.TemperatureLog;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.TemperatureLogId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.repository.TemperatureLogRepository;
import br.com.luizmariodev.ems.temperature.monitoring.infraestructure.listener.message.TemperatureLogMessage;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    public void save(TemperatureLogMessage temperatureLogMessage) {
        var temperatureLog = new TemperatureLog();
        temperatureLog.setId(new TemperatureLogId(temperatureLogMessage.getId()));
        temperatureLog.setValue(BigDecimal.valueOf(temperatureLogMessage.getValue()));
        temperatureLog.setRegisteredAt(temperatureLogMessage.getRegisteredAt());
        temperatureLog.setSensorId(new SensorId(temperatureLogMessage.getSensorId()));
        repository.save(temperatureLog);
    }
}
