package com.sravanth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sravanth.dto.PetDTO;
import com.sravanth.dto.PetStatisticsDTO;
import com.sravanth.dto.UpdatePetDTO;
import com.sravanth.exceptions.PetNotFoundException;
import com.sravanth.service.PetService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/pets")
@RestController
public class PetController {
	
	private final PetService petService;

	@PostMapping 
	public ResponseEntity<Integer> savePet(@RequestBody PetDTO petDTO){
		int petId = petService.savePet(petDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(petId);
	}

	@GetMapping ("/{petId}")
	public ResponseEntity<PetDTO> finPet(@PathVariable int petId) throws PetNotFoundException{
		PetDTO petDTO = petService.findPet(petId);
		return ResponseEntity.status(HttpStatus.FOUND).body(petDTO);
	}

	@PatchMapping ("/{petId}")
	public ResponseEntity<Void> updatePetName(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO) throws PetNotFoundException{
		petService.updatePetName(petId, updatePetDTO.name());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping ("/{petId}")
	public ResponseEntity<Void> deletePet(@PathVariable int petId) throws PetNotFoundException{
		petService.deletePet(petId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/stats")
	public ResponseEntity<PetStatisticsDTO> getStatistics() {
		PetStatisticsDTO petStatisticsDTO = petService.getStatistics();
		return ResponseEntity.status(HttpStatus.OK).body(petStatisticsDTO);
	}

}
