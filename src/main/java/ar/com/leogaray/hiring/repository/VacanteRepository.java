package ar.com.leogaray.hiring.repository;

import ar.com.leogaray.hiring.model.VacanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VacanteRepository extends JpaRepository<VacanteEntity, Long> {
}
