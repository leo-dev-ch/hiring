package ar.com.leogaray.hiring.repository;

import ar.com.leogaray.hiring.model.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
}
