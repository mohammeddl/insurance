package com.insurance.service;

import com.insurance.model.AssuranceAutomobile;
import com.insurance.repository.AssuranceAutomobileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AssuranceAutomobileServiceTest {

    @Mock
    private AssuranceAutomobileRepository assuranceAutomobileRepository;

    @InjectMocks
    private AssuranceAutomobileService assuranceAutomobileService;

    private AssuranceAutomobile assuranceAutomobile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assuranceAutomobile = new AssuranceAutomobile();
        assuranceAutomobile.setId(1L);
        assuranceAutomobile.setAgeConducteur(22);
        assuranceAutomobile.setTypeVehicule("Luxe");
        assuranceAutomobile.setUtilisationVehicule("Professionnelle");
        assuranceAutomobile.setHistoriqueConduite("Sans Sinistre");
    }

    @Test
    void testCalculerDevisAutomobile() {
        // Act
        double calculatedAmount = assuranceAutomobileService.calculerDevisAutomobile(assuranceAutomobile);

        // Assert
        assertEquals(621.0, calculatedAmount, 0.1); // Expected result based on the conditions
        verify(assuranceAutomobileRepository, times(1)).save(assuranceAutomobile);
    }

    @Test
    void testUpdateAssuranceAutomobile() {
        // Act
        assuranceAutomobileService.updateAssuranceAutomobile(assuranceAutomobile);

        // Assert
        verify(assuranceAutomobileRepository, times(1)).save(assuranceAutomobile);
    }

    @Test
    void testFindAssuranceAutomobileByUtilisateur_Id() {
        // Arrange
        Long utilisateurId = 1L;
        AssuranceAutomobile auto1 = new AssuranceAutomobile();
        AssuranceAutomobile auto2 = new AssuranceAutomobile();
        List<AssuranceAutomobile> autoList = Arrays.asList(auto1, auto2);

        when(assuranceAutomobileRepository.findByUtilisateur_Id(utilisateurId)).thenReturn(autoList);

        // Act
        List<AssuranceAutomobile> result = assuranceAutomobileService.findAssuranceAutomobileByUtilisateur_Id(utilisateurId);

        // Assert
        assertEquals(2, result.size());
        verify(assuranceAutomobileRepository, times(1)).findByUtilisateur_Id(utilisateurId);
    }

    @Test
    void testFindById_Success() {
        // Arrange
        when(assuranceAutomobileRepository.findById(1L)).thenReturn(Optional.of(assuranceAutomobile));

        // Act
        AssuranceAutomobile result = assuranceAutomobileService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(assuranceAutomobile.getId(), result.getId());
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(assuranceAutomobileRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        AssuranceAutomobile result = assuranceAutomobileService.findById(2L);

        // Assert
        assertNull(result);
    }
}
