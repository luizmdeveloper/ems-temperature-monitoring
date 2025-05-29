package br.com.luizmariodev.ems.temperature.monitoring.domain.repository;

import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorAlert;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorAlertRepository extends JpaRepository<SensorAlert, SensorId> {
}
