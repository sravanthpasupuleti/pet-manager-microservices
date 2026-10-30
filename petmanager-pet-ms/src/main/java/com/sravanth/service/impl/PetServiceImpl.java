package com.sravanth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sravanth.dto.PetCategoryStatisticsDTO;
import com.sravanth.dto.PetDTO;
import com.sravanth.dto.PetGenderStatisticsDTO;
import com.sravanth.dto.PetStatisticsDTO;
import com.sravanth.entity.Pet;
import com.sravanth.enums.Gender;
import com.sravanth.enums.PetType;
import com.sravanth.exceptions.PetNotFoundException;
import com.sravanth.util.PetMapper;
import com.sravanth.repository.PetRepository;
import com.sravanth.service.PetService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

	private final PetRepository petRepository;
	private final PetMapper petMapper;

	@Value ("${pet.not.found}")
	private String petNotFound;

	@Override
	public int savePet(PetDTO petDTO) {
		Pet pet = petMapper.petDTOToPet(petDTO);
		petRepository.save(pet);
		return pet.getId();
	}

	@Override
	public PetDTO findPet(int petId) throws PetNotFoundException {
		return petRepository.findById(petId).map(petMapper::petToPetDTO).orElseThrow(() -> new PetNotFoundException(String.format(petNotFound, petId)));
	}

	@Override
	public void updatePetName(int petId, String petName) throws PetNotFoundException {
		Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException(String.format(petNotFound, petId)));
		pet.setName(petName);
		petRepository.save(pet);
	}

	@Override
	public void deletePet(int petId) throws PetNotFoundException {
		if (petRepository.existsById(petId)) {
			petRepository.deleteById(petId);
		}else{
			throw new PetNotFoundException(String.format(petNotFound, petId));
		}
	}

	@Override
	public PetStatisticsDTO getStatistics() {
		PetStatisticsDTO petStatisticsDTO = new PetStatisticsDTO();
		List<Object[]> rows = petRepository.fetchStatistics();
		for (Object[] row : rows) {
			String category = (String) row[0];
			Gender gender = (Gender) row[1];
			PetType type = (PetType) row[2];
			long count = (Long) row[3];
			petStatisticsDTO.incrementTotal(count);
			PetCategoryStatisticsDTO petCategoryStatisticsDTO = petStatisticsDTO.getOrCreateCategory(category);
			petCategoryStatisticsDTO.incrementTotal(count);
			PetGenderStatisticsDTO petGenderStatisticsDTO = petCategoryStatisticsDTO.getOrCreateGender(gender);
			petGenderStatisticsDTO.incrementTotal(count);
			petGenderStatisticsDTO.mergeOrCreateType(type, count);
		}
		return petStatisticsDTO;
	}

}
