package ar.com.leogaray.hiring.repository;

import ar.com.leogaray.hiring.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
}
