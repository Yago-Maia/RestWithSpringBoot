package br.com.yago.controller;

import br.com.yago.data.vo.PersonVO;
import br.com.yago.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public PersonVO findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @GetMapping
    public List<PersonVO> findAll() { return personService.findAll(); }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
