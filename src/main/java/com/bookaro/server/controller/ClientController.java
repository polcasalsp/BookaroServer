package com.bookaro.server.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookaro.server.model.Client;
import com.bookaro.server.service.ClientService;

@RestController
@RequestMapping("api/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("")
	public ArrayList<Client> getAllClients(){
		return clientService.findAll();
	}
	
	@GetMapping (value = "{id}")
	public Optional<Client> getClientId (@PathVariable ("id")long id) {
		return clientService.findById(id);
	}
	
	@PostMapping("")
	public String addClient (@RequestBody Client client) {
		if (client != null) {
			clientService.add(client);
			return "Added a client";
		} else {
			return "Request does not contain a body";
		}
	}
	
	@PutMapping("")
	public String updateClient (@RequestBody Client client) {
	    if(client != null) {
	    	clientService.update(client);
	        return "Updated employee.";
	    } else {
	        return "Request does not contain a body";
	    }
	}
	
	@DeleteMapping("{id}")
	public String deleteClient (@PathVariable("id") long id) {
		if(id > 0) {
			if(clientService.delete(id)) {
				return "Deleted the client.";
			} else {
				return "Cannot delete the client.";
			}
		}
		return "The id is invalid for the client.";
	}

	

}
