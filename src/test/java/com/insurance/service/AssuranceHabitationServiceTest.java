package com.insurance.service;

import com.insurance.model.AssuranceHabitation;
import com.insurance.repository.AssuranceHabitationRepository;
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

public class AssuranceHabitationServiceTest {

    @Mock
    private AssuranceHabitationRepository assuranceHabitationRepository;

    @InjectMocks
    private AssuranceHabitationService assuranceHabitationService;

    private AssuranceHabitation habitation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        habitation = new AssuranceHabitation();
        habitation.setId(1L);
        habitation.setTypeLogement("Maison");
        habitation.setLocalisation("Zone à risque");
        habitation.setValeurBien(250000);
        habitation.setSystemeSecurite(true);
    }

    @Test
    void testCalculerDevisHabitation() {
        double calculatedAmount = assuranceHabitationService.calculerDevisHabitation(habitation);

        assertEquals(300.4155, calculatedAmount, 0.1); 
        verify(assuranceHabitationRepository, times(1)).save(habitation);
    }

    @Test
    void testUpdateAssuranceHabitation() {
        assuranceHabitationService.updateAssuranceHabitation(habitation);

        verify(assuranceHabitationRepository, times(1)).save(habitation);
    }

    @Test
    void testFindAssuranceHabitationByUtilisateur_Id() {
        Long utilisateurId = 1L;
        AssuranceHabitation habitation1 = new AssuranceHabitation();
        AssuranceHabitation habitation2 = new AssuranceHabitation();
        List<AssuranceHabitation> habitationList = Arrays.asList(habitation1, habitation2);

        when(assuranceHabitationRepository.findByUtilisateur_Id(utilisateurId)).thenReturn(habitationList);

        List<AssuranceHabitation> result = assuranceHabitationService.findAssuranceHabitationByUtilisateur_Id(utilisateurId);

        assertEquals(2, result.size());
        verify(assuranceHabitationRepository, times(1)).findByUtilisateur_Id(utilisateurId);
    }

    @Test
    void testFindById_Success() {

        when(assuranceHabitationRepository.findById(1L)).thenReturn(Optional.of(habitation));

        AssuranceHabitation result = assuranceHabitationService.findById(1L);

        assertNotNull(result);
        assertEquals(habitation.getId(), result.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(assuranceHabitationRepository.findById(2L)).thenReturn(Optional.empty());


        AssuranceHabitation result = assuranceHabitationService.findById(2L);

        assertNull(result);
    }
}
