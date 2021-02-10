package br.com.yago.service;

import br.com.yago.converter.DozerConverter;
import br.com.yago.data.vo.PersonVO;
import br.com.yago.exception.ResourceNotFoundException;
import br.com.yago.data.model.Person;
import br.com.yago.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonVO findById(Long id) {
        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        return DozerConverter.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        var entity = DozerConverter.parseObject(person, Person.class);
        return DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        PersonVO p = findById(person.getId());

        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setAddress(person.getAddress());
        p.setGender(person.getGender());

        return DozerConverter.parseObject(personRepository.save(DozerConverter.parseObject(p, Person.class)), PersonVO.class);
    }

    public void delete(Long id) {
        personRepository.delete(DozerConverter.parseObject(findById(id), Person.class));
    }
}
