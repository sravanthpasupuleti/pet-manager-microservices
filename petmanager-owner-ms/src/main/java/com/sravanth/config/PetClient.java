package com.sravanth.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sravanth.dto.PetDTO;
import com.sravanth.dto.UpdatePetDTO;

@FeignClient (name = "petmanager-pet-ms")
public interface PetClient {

    @PostMapping ("/pets")
    int savePet(@RequestBody PetDTO petDTO);

    @GetMapping ("/pets/{petId}")
    PetDTO findPetDTO(@PathVariable int petId);

    @PatchMapping ("/pets/{petId}")
    void updatePetDetails(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO);

    @DeleteMapping ("/pets/{petId}")
    void deletePet(@PathVariable int petId);
}
