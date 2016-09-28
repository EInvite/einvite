package com.einvite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.einvite.data.entities.Event;
import com.einvite.data.repo.EventRepository;

@RestController
public class EventController {

	@Autowired
	EventRepository eventRepository;
	
	@RequestMapping("/event/save")
	public void saveEvent(@ModelAttribute Event event) {
		eventRepository.save(event);
	}
}
