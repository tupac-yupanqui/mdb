package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.RoleEntity;
import de.wko.mdb.types.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole name);
}