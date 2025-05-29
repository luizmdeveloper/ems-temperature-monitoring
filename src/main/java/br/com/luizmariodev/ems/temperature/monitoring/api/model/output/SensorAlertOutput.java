package br.com.luizmariodev.ems.temperature.monitoring.api.model.output;

import io.hypersistence.tsid.TSID;
import lombok.Data;

@Data
public class SensorAlertOutput {

    private TSID id;
    private Double maxTemperature;
    private Double minTemperature;

}
