package com.sravanth.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString(callSuper = true)
@Setter
@Getter
public class DomesticPetDTO extends PetDTO {

	private LocalDate birthDate;

}
