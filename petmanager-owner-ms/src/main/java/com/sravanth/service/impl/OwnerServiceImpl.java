package com.sravanth.service.impl;

import static com.sravanth.enums.MailType.EXIT;
import static com.sravanth.enums.MailType.MODIFY;
import static com.sravanth.enums.MailType.WELCOME;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sravanth.dto.MailDTO;
import com.sravanth.dto.OwnerDTO;
import com.sravanth.entity.Owner;
import com.sravanth.exception.OwnerNotFoundException;
import com.sravanth.repository.OwnerRepository;
import com.sravanth.service.MailService;
import com.sravanth.service.OwnerService;
import com.sravanth.util.OwnerMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
	
	private final OwnerRepository ownerRepository;
	private final OwnerMapper ownerMapper;
	private final MailService mailService;
	@Value("${owner.not.found}")
	private String ownerNotFound;
	
	@Override
	public Integer saveOwner(OwnerDTO ownerDTO) {
		Owner owner = ownerMapper.ownerDTOToOwner(ownerDTO);
		ownerRepository.save(owner);
		MailDTO mailDTO = new MailDTO(ownerDTO.getEmailId(), ownerDTO.getFirstName(), ownerDTO.getLastName(), WELCOME);
		log.info(mailService.sendEmail(mailDTO));
		return owner.getId();
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		return ownerRepository.findById(ownerId)
				.map(ownerMapper::ownerToOwnerDTO)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
	}
	
	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		owner.getPet().setName(petName);
		ownerRepository.save(owner);
		MailDTO mailDTO = new MailDTO(owner.getEmailId(), owner.getFirstName(), owner.getLastName(), MODIFY);
		log.info(mailService.sendEmail(mailDTO));
	}
	
	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		ownerRepository.deleteById(ownerId);
		MailDTO mailDTO = new MailDTO(owner.getEmailId(), owner.getFirstName(), owner.getLastName(), EXIT);
		log.info(mailService.sendEmail(mailDTO));
	}
	
	@Override
	public Page<OwnerDTO> findAllOwners(Pageable pageable) {
		return ownerRepository.findAll(pageable)
				.map(ownerMapper::ownerToOwnerDTO);
	}

}
