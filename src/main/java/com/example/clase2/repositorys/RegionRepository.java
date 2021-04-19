package com.example.clase2.repositorys;

import com.example.clase2.entitys.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
}
