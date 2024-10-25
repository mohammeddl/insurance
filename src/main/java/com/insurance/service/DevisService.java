
package com.insurance.service;
import com.insurance.model.Devis;
import java.util.List;

public interface DevisService {

    Devis findDevisById(Long id);

    List<Devis> findDevisByUtilisateurId(Long id);

}
