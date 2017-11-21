package com.recruitment.crud;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClientsDatatable implements Serializable{
    
    @ManagedProperty("#{clientsDatabase}")
    private ClientsDatabase database;
    
    private List<Client> clients;
    
    @PostConstruct
    public void init() {
        refresh();
    }
    
    public void refresh(){
        clients = database.select(c->true);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setDatabase(ClientsDatabase database) {
        this.database = database;
    }

    
}
