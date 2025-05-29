package br.com.luizmariodev.ems.temperature.monitoring.api.model.input;

import lombok.Data;

@Data
public class SensorAlertInput {

    private Double maxTemperature;
    private Double minTemperature;

}
