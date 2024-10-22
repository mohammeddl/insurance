package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    
} 