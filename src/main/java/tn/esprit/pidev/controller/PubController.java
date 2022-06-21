package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Publication;
import tn.esprit.pidev.services.api.PublicationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PubController {

    private Publication publication;
    @Autowired
     PublicationService ps;

    @GetMapping("/getAllPub")
    //@RequestBody
    public List<Publication> getAllPub(){
        return ps.findAll();
    }

    @GetMapping("/getById/{id}")
    //@RequestBody
    public Optional<Publication> getPub(@PathVariable("id") Long id){
        return ps.findOne(id);
    }

    @PostMapping("/addPub")
    public Publication addPub(@RequestBody Publication publication){
        return ps.save(publication);
    }
}
