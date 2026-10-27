package com.sravanth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sravanth.dto.OwnerDTO;
import com.sravanth.exception.OwnerNotFoundException;


public interface OwnerService {

	Integer saveOwner(OwnerDTO ownerDTO);

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	Page<OwnerDTO> findAllOwners(Pageable pageable);

}
