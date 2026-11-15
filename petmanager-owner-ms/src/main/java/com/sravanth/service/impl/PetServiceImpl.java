package com.sravanth.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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

    private final RestClient loadBalancedRestClient;

    @Value("${pet.service.base.uri}")
    private String petServiceBaseUri;


    @Override
    public int savePet(PetDTO petDTO) {
        ResponseEntity<Integer> response = loadBalancedRestClient.post()
        .uri(petServiceBaseUri)
        .body(petDTO)
        .retrieve()
        .toEntity(Integer.class);
        return response.getBody();
    }

    @Retry (name = "petRetry", fallbackMethod = "findPetDTOFallBack")
    @Override
    public PetDTO finPetDTO(int petId) {
        log.info("===== In findPet =====");
        ResponseEntity<PetDTO> response = loadBalancedRestClient.get()
        .uri(petServiceBaseUri + "/{petId}", petId)
        .retrieve()
        .toEntity(PetDTO.class);
        return response.getBody();
    }

    public PetDTO findPetDTOFallBack(int petId, Throwable exception){
        log.info("===== In findPetFallback =====");
		log.error("Pet service failed for petId {} due to {}", petId, exception.getMessage());
        return null;
    }

    @Retry (name = "petRetry")
    @Override
    public void updatePetDetails(int petId, String petName) {
        UpdatePetDTO updatePetDTO = new UpdatePetDTO(petName);
        loadBalancedRestClient.patch()
        .uri(petServiceBaseUri + "/{petId}", petId)
        .body(updatePetDTO)
        .retrieve()
        .toBodilessEntity();
    }

    @Override
    public void deletePet(int petId) {
        loadBalancedRestClient.delete()
        .uri(petServiceBaseUri + "/{petId}", petId)
        .retrieve()
        .toBodilessEntity();
    }

}
