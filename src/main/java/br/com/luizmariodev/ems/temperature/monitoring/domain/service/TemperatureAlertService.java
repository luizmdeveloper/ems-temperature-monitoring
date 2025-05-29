package br.com.luizmariodev.ems.temperature.monitoring.domain.service;

import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.repository.SensorAlertRepository;
import br.com.luizmariodev.ems.temperature.monitoring.infraestructure.listener.message.TemperatureLogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TemperatureAlertService {

    private final SensorAlertRepository sensorAlertRepository;

    public TemperatureAlertService(SensorAlertRepository sensorAlertRepository) {
        this.sensorAlertRepository = sensorAlertRepository;
    }

    public void verify(TemperatureLogMessage temperatureLogMessage) {
        var optionalAlert = sensorAlertRepository.findById(new SensorId(temperatureLogMessage.getSensorId()));

        optionalAlert.ifPresentOrElse(
                sensorAlert -> {
                    if (sensorAlert.verifyTemperatureMaxGreaterThan(temperatureLogMessage.getValue())) {
                        log.info("Alert sensor {} temperature greater than {}", temperatureLogMessage.getSensorId(), temperatureLogMessage.getValue());
                    } else if (sensorAlert.verifyTemperatureMinLessThan(temperatureLogMessage.getValue())) {
                        log.info("Alert sensor {} temperature less than {}", temperatureLogMessage.getSensorId(), temperatureLogMessage.getValue());
                    } else {
                        logIgnoreAlert("Alert ignore sensor {} temperature {}", temperatureLogMessage);
                    }
                },
                () -> logIgnoreAlert("Alert ignore sensor {} temperature {}", temperatureLogMessage));
    }

    private void logIgnoreAlert(String format, TemperatureLogMessage temperatureLogMessage) {
        log.info(format, temperatureLogMessage.getSensorId(), temperatureLogMessage.getValue());
    }

}
