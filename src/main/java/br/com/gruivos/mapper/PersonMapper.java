package br.com.gruivos.mapper;

import br.com.gruivos.data.vo.PersonVO;
import br.com.gruivos.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonVO personToPersonVo(Person person);
    List<PersonVO> personListToPersonVoList(List<Person> person);  // Renomeado o método

    Person personVoToPerson(PersonVO personVo);
    List<Person> personVoListToPersonList(List<PersonVO> personVo);  // Renomeado o método
}
