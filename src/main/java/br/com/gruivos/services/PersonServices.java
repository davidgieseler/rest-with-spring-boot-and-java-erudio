package br.com.gruivos.services;

import br.com.gruivos.data.vo.PersonVO;
import br.com.gruivos.exceptions.ResourceNotFoundException;
import br.com.gruivos.mapper.GrMapper;
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

    public PersonVO findById(Long id) {

        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return GrMapper.parseObject(person, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all person!");


        return GrMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO personVo) {
        logger.info("Creating one person!");

        var person = GrMapper.parseObject(personVo, Person.class);
        var vo =  GrMapper.parseObject(repository.save(person), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO personVo) {
        logger.info("Updating one person!");

        Person person = repository.findById(personVo.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        person.setFirstName(personVo.getFirstName());
        person.setLastName(personVo.getLastName());
        person.setAddress(personVo.getAddress());
        person.setGender(personVo.getGender());

        return GrMapper.parseObject(repository.save(person), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
