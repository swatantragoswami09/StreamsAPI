package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Streams;
import com.example.demo.repository.StreamsRepository;

import org.springframework.beans.BeanUtils;



@RestController
public class MyController {
	
	@Autowired
	private StreamsRepository streamsRepository;
	

	@GetMapping
	public List<Streams> getStreams() {
		return streamsRepository.findAll();
	}
	
    @GetMapping("{id}")
    public Streams getStreamById(@PathVariable Long id){
        return streamsRepository.getById(id);
    }
	
	@PostMapping
	public Streams postStream(@RequestBody Streams streams) {
		return streamsRepository.saveAndFlush(streams);
	}
	@PutMapping(value = "{id}")
	public Streams updateStream(@PathVariable Long id,@RequestBody Streams streams) {
		Streams existingStream = streamsRepository.getById(id);
		BeanUtils.copyProperties(streams,existingStream,"id");		
		return streamsRepository.saveAndFlush(existingStream);
	}
	@DeleteMapping(value = "{id}")
	public String deleteStream(@PathVariable Long id) {
		 streamsRepository.deleteById(id);
		 return "deleted from database";
		
	}
}
