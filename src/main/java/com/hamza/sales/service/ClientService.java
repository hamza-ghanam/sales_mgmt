package com.hamza.sales.service;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Client;
import com.hamza.sales.repository.ClientRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository repo;

    public List<Client> getAllClients() {
        List<Client> clients = repo.findAll();

        if (clients.size() > 0) {
            return clients;
        } else {
            return new ArrayList<>();
        }
    }

    public Client getClient(Integer id) {
        Client client = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found :: ", "ID", id));

        return client;
    }

    public Client createClient(Client entity) {
        repo.save(entity);

        return entity;
    }

    public Client updateClient(Integer id, Client entity) {
        Client client = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found :: ", "ID", id));

        client.setName(entity.getName());
        client.setLastName(entity.getLastName());
        client.setMobile(entity.getMobile());

        return repo.save(client);
    }
}
