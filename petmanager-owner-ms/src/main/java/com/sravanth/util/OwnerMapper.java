package com.sravanth.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.sravanth.dto.OwnerDTO;
import com.sravanth.entity.Owner;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {
	
	@Mapping(target = "petId", source = "petDTO.id")
	Owner ownerDTOToOwner(OwnerDTO ownerDTO);
	
	@Mapping(target = "petDTO", ignore = true)
	OwnerDTO ownerToOwnerDTO(Owner owner);
}
