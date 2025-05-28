package br.com.luizmariodev.ems.temperature.monitoring.infraestructure.listener;

import br.com.luizmariodev.ems.temperature.monitoring.infraestructure.listener.message.TemperatureLogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQListener {

    @RabbitListener(queues = "${queue.temperature.processing}")
    public void handle(@Payload TemperatureLogMessage temperatureLogMessage) {
        log.info(String.format("Sensor %s temperatura %f", temperatureLogMessage.getSensorId(), temperatureLogMessage.getValue()));
    }
}
