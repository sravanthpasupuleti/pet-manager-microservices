package com.sravanth.service;

import com.sravanth.dto.PetDTO;

public interface PetService {

    int savePet(PetDTO petDTO);

    PetDTO finPetDTO(int petId);

    void updatePetDetails(int petId, String petName);

    void deletePet(int petId);
}
