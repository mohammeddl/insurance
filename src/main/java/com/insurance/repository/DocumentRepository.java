package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.Document;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    
} 