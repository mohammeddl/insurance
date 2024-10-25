package com.insurance.service.impl;
import com.insurance.model.Devis;
import com.insurance.repository.DevisRepository;
import com.insurance.service.DevisService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DevisServiceImpl implements DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Override
    public Devis findDevisById(Long id) {
        return devisRepository.findById(id).orElse(null);
    }

    //find devis with all sub-entities by utilisateur id
    @Override
    public List<Devis> findDevisByUtilisateurId(Long id) {
        return devisRepository.findDevisByUtilisateurId(id);
    }


   

}
