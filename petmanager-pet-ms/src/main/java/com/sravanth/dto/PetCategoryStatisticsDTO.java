package com.sravanth.dto;

import java.util.EnumMap;
import java.util.Map;

import com.sravanth.enums.Gender;

import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class PetCategoryStatisticsDTO {
	
	private long total;
	private Map<Gender, PetGenderStatisticsDTO> gender = new EnumMap<>(Gender.class);
	
	public void incrementTotal(long count) {
		this.total += count;
	}

	public PetGenderStatisticsDTO getOrCreateGender(Gender gender) {
		return this.gender.computeIfAbsent(
				gender, _ -> new PetGenderStatisticsDTO());
	}

}
