package br.com.luizmariodev.ems.temperature.monitoring.domain.service;

import br.com.luizmariodev.ems.temperature.monitoring.infraestructure.listener.message.TemperatureLogMessage;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TemperatureRegisterService {

    private final SensorMonitoringService sensorMonitoringService;
    private final TemperatureLogService temperatureLogService;

    public TemperatureRegisterService(SensorMonitoringService sensorMonitoringService, TemperatureLogService temperatureLogService) {
        this.sensorMonitoringService = sensorMonitoringService;
        this.temperatureLogService = temperatureLogService;
    }

    @Transactional
    public void registered(TemperatureLogMessage temperatureLogMessage) {
        var optionalSensorMonitoring = sensorMonitoringService.findBySensorId(temperatureLogMessage.getSensorId());

        optionalSensorMonitoring.ifPresentOrElse(
                sensorMonitoringOutput -> createTemperature(temperatureLogMessage),
                () -> logTemperatureIgnored(temperatureLogMessage));
    }

    private void createTemperature(TemperatureLogMessage temperatureLogMessage) {
        sensorMonitoringService.update(temperatureLogMessage.getSensorId(), temperatureLogMessage.getValue(), temperatureLogMessage.getRegisteredAt());
        temperatureLogService.save(temperatureLogMessage);
        log.info("Temperature registered success sensor {} temperature {}", temperatureLogMessage.getSensorId(), temperatureLogMessage.getValue());
    }

    private void logTemperatureIgnored(TemperatureLogMessage temperatureLogMessage) {
        log.info("Temperature ignored sensor {} temperature {}", temperatureLogMessage.getSensorId(), temperatureLogMessage.getValue());
    }
}
