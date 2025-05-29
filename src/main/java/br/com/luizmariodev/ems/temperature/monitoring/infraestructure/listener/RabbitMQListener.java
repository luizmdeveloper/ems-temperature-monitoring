package br.com.luizmariodev.ems.temperature.monitoring.infraestructure.listener;

import br.com.luizmariodev.ems.temperature.monitoring.domain.service.TemperatureAlertService;
import br.com.luizmariodev.ems.temperature.monitoring.domain.service.TemperatureRegisterService;
import br.com.luizmariodev.ems.temperature.monitoring.infraestructure.listener.message.TemperatureLogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQListener {

    private final TemperatureRegisterService temperatureRegisterService;
    private final TemperatureAlertService temperatureAlertService;

    public RabbitMQListener(TemperatureRegisterService temperatureRegisterService, TemperatureAlertService temperatureAlertService) {
        this.temperatureRegisterService = temperatureRegisterService;
        this.temperatureAlertService = temperatureAlertService;
    }

    @RabbitListener(queues = "${queue.temperature.processing}")
    public void handleProcessing(@Payload TemperatureLogMessage temperatureLogMessage) {
        temperatureRegisterService.registered(temperatureLogMessage);
    }

    @RabbitListener(queues = "${queue.temperature.alerting}")
    public void handleAlerting(@Payload TemperatureLogMessage temperatureLogMessage) {
        temperatureAlertService.verify(temperatureLogMessage);
    }
}
