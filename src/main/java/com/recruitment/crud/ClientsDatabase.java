package com.recruitment.crud;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ClientsDatabase implements Database<Client>, Serializable{
    private static int idCounter = 0;
    
    private final Map<Integer, Client> clients = new HashMap<>();
    
    @Override
    public void insert(Client entity) {
        entity.setId(++idCounter);
        entity.setCreationDate(new Date());
        clients.put(idCounter, entity);
    }

    @Override
    public void update(Client entity) {
        Client client = clients.get(entity.getId());
        client.setFirstName(entity.getFirstName());
        client.setLastName(entity.getLastName());
        client.setPhoneNumber(entity.getPhoneNumber());
        client.setComment(entity.getComment());
    }

    @Override
    public void delete(Client entity) {
        clients.remove(entity.getId());
    }

    @Override
    public List<Client> select(Predicate<Client> p) {
        return clients.values().stream().filter(p).collect(Collectors.toList());
    }
    
}
