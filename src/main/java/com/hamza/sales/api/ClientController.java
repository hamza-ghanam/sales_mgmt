package com.hamza.sales.api;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Client;
import com.hamza.sales.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    ClientService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> list = service.getAllClients();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable(value = "id") Integer id) {
        Client client = service.getClient(id);

        return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws ResourceNotFoundException {
        Client cl = service.createClient(client);

        return new ResponseEntity<>(cl, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable(value = "id") Integer id,
            @RequestBody Client client)
            throws ResourceNotFoundException {

        Client cl = service.updateClient(id, client);

        return new ResponseEntity<>(cl, new HttpHeaders(), HttpStatus.OK);
    }

}
