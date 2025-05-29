package br.com.luizmariodev.ems.temperature.monitoring.api.controller;

import br.com.luizmariodev.ems.temperature.monitoring.api.model.output.TemperatureLogOutput;
import br.com.luizmariodev.ems.temperature.monitoring.domain.service.TemperatureLogService;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sensors/{sensorId}/logs")
public class TemperatureLogController {

    private final TemperatureLogService service;

    public TemperatureLogController(TemperatureLogService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TemperatureLogOutput> findAll(@PathVariable TSID sensorId, Pageable pageable) {
        return service.findAllBySensor(sensorId, pageable);
    }
}
