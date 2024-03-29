package com.Gestao.Pessoas.mapper;

import com.Gestao.Pessoas.DTO.PersonDTO;
import com.Gestao.Pessoas.Entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Mapper(componentModel = "spring",imports = {Arrays.class})
public interface MapperPerson {

    MapperPerson INSTACE = Mappers.getMapper(MapperPerson.class);

    @Mapping(target = "birthdate" ,dateFormat = "dd-MM-yyyy")
    Person PersonDtoToPerson(PersonDTO personDTO);

    PersonDTO toDTO(Person person);

}
