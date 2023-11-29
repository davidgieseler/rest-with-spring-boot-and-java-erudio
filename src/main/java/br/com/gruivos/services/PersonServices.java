package br.com.gruivos.services;

import br.com.gruivos.data.vo.PersonVO;
import br.com.gruivos.exceptions.ResourceNotFoundException;
import br.com.gruivos.mapper.PersonMapper;
import br.com.gruivos.model.Person;
import br.com.gruivos.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    private PersonMapper personMapper;

    public PersonVO findById(Long id) {

        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

//        return GrMapper.parseObject(person, PersonVO.class);
        return personMapper.personToPersonVo(person);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all person!");


//        return GrMapper.parseListObjects(repository.findAll(), PersonVO.class);
        return personMapper.personListToPersonVoList(repository.findAll());
    }

    public PersonVO create(PersonVO personVo) {
        logger.info("Creating one person!");

//        var person = GrMapper.parseObject(personVo, Person.class);
//        var vo =  GrMapper.parseObject(repository.save(person), PersonVO.class);
        var person = personMapper.personVoToPerson(personVo);
        var vo = personMapper.personToPersonVo(repository.save(person));

//        return vo;
        return vo;
    }

    public PersonVO update(PersonVO personVo) {
        logger.info("Updating one person!");

        Person person = repository.findById(personVo.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        person.setFirstName(personVo.getFirstName());
        person.setLastName(personVo.getLastName());
        person.setAddress(personVo.getAddress());
        person.setGender(personVo.getGender());

//        return GrMapper.parseObject(repository.save(person), PersonVO.class);
        return personMapper.personToPersonVo(repository.save(person));
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
