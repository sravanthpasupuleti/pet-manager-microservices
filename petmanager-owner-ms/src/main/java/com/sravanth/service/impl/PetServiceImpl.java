package com.sravanth.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.sravanth.dto.PetDTO;
import com.sravanth.dto.UpdatePetDTO;
import com.sravanth.service.PetService;

import lombok.RequiredArgsConstructor;

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

    @Override
    public PetDTO finPetDTO(int petId) {
        ResponseEntity<PetDTO> response = loadBalancedRestClient.get()
        .uri(petServiceBaseUri + "/{petId}", petId)
        .retrieve()
        .toEntity(PetDTO.class);
        return response.getBody();
    }

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
