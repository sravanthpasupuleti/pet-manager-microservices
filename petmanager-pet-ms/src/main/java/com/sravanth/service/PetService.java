package com.sravanth.service;

import com.sravanth.dto.PetDTO;
import com.sravanth.dto.PetStatisticsDTO;
import com.sravanth.exceptions.PetNotFoundException;


public interface PetService {

	int savePet(PetDTO petDTO);

	PetDTO findPet(int petId) throws PetNotFoundException;

	void updatePetName(int petId, String petName) throws PetNotFoundException;

	void deletePet(int petId) throws PetNotFoundException;

	PetStatisticsDTO getStatistics();

}
