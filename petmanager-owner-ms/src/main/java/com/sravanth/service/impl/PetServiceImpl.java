package com.sravanth.service.impl;

import org.springframework.stereotype.Service;

import com.sravanth.config.PetClient;
import com.sravanth.dto.PetDTO;
import com.sravanth.dto.UpdatePetDTO;
import com.sravanth.service.PetService;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
@RequiredArgsConstructor 
public class PetServiceImpl implements PetService{

    private final PetClient petClient;

    @Override
    public int savePet(PetDTO petDTO) {
        return petClient.savePet(petDTO);
    }

    // @Retry (name = "petRetry", fallbackMethod = "findPetDTOFallBack")
    @Override
    public PetDTO findPetDTO(int petId) {
        return petClient.findPetDTO(petId);
    }

    public PetDTO findPetDTOFallBack(int petId, Throwable exception){
        return null;
    }

    @Retry (name = "petRetry")
    @Override
    public void updatePetDetails(int petId, UpdatePetDTO updatePetDTO) {
        petClient.updatePetDetails(petId, updatePetDTO);
    }

    @Override
    public void deletePet(int petId) {
        petClient.deletePet(petId);
    }

}
