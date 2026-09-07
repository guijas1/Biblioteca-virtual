package com.guijas1.ReaderAssisstant.repository;

import com.guijas1.ReaderAssisstant.entity.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadRepository extends JpaRepository<ReaderEntity, Long> {


}
