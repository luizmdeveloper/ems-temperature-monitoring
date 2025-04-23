package br.com.luizmariodev.ems.temperature.monitoring.api.controller;

import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/sensors/{sensorId}/temperatures/data")
@Slf4j
public class TemperatureMonitoringController {

    @PostMapping(consumes = MediaType.TEXT_PLAIN)
    public void data(@PathVariable TSID sensorId, @RequestBody String text) {
        if (text == null || text.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Double value;

        try {
            value = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        var
    }
}
