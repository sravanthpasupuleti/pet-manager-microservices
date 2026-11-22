package com.sravanth.service;

import com.sravanth.dto.PetDTO;
import com.sravanth.dto.UpdatePetDTO;

public interface PetService {

    int savePet(PetDTO petDTO);

    PetDTO findPetDTO(int petId);

    void updatePetDetails(int petId, UpdatePetDTO updatePetDTO);

    void deletePet(int petId);
}
