package com.example.clase2.repositorys;

import com.example.clase2.entitys.RegionEntity;
import com.example.clase2.entitys.TerritorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerritorieRepository extends JpaRepository<TerritorieEntity, String> {

    List<TerritorieEntity> findByTerritorydescription(String description);

    @Query(value="select * from territories where TerritoryDescription like %?1%", nativeQuery = true)
    List<TerritorieEntity> buscarDescription2(String description);
}
