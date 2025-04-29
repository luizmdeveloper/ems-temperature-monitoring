package br.com.luizmariodev.ems.temperature.monitoring.domain.repository;

import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorId;
import br.com.luizmariodev.ems.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId> {
}
