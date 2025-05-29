package br.com.luizmariodev.ems.temperature.monitoring.api.controller;

import br.com.luizmariodev.ems.temperature.monitoring.api.model.output.SensorMonitoringOutput;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.service.SensorMonitoringService;
import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sensors/{sensorId}/monitoring")
@Slf4j
public class SensorMonitoringController {

    private final SensorMonitoringService service;

    public SensorMonitoringController(SensorMonitoringService service) {
        this.service = service;
    }

    @GetMapping
    public SensorMonitoringOutput detail(@PathVariable TSID sensorId) {
        return service.detail(new SensorId(sensorId));
    }

    @PutMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable TSID sensorId) {
        service.enable(new SensorId(sensorId));
    }

    @DeleteMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {
        service.disable(new SensorId(sensorId));
    }
}
