package com.cloudfiles.server.repository;

import com.cloudfiles.server.model.AmazonFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazonFileRepository extends JpaRepository<AmazonFile, Integer> {}
