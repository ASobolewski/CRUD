package com.recruitment.crud.controllers;

import com.recruitment.crud.Client;
import com.recruitment.crud.ClientForm;
import com.recruitment.crud.ClientsDatabase;
import com.recruitment.crud.ClientsDatatable;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ClientController implements Serializable {

    @ManagedProperty("#{clientsDatabase}")
    private ClientsDatabase database;

    @ManagedProperty("#{clientsDatatable}")
    private ClientsDatatable clientsDatatable;

    @ManagedProperty("#{clientForm}")
    private ClientForm clientForm;

    private String searchField;
    private String searchFieldValue;

    public List<Client> getClients() {
        clientsDatatable.setClients(database.select(c -> true));
        return clientsDatatable.getClients();
    }

    public void removeClient(Client client) {
        database.delete(client);
        clientsDatatable.refresh();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully deleted client with " + client.getId() + " Id"));
    }

    public void saveClient(Client client) {
        if (client.getId() != null && !database.select(c -> c.getId().intValue() == client.getId().intValue()).isEmpty()) {
            database.update(client);
        } else {
            database.insert(client);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client Saved"));
        clientsDatatable.refresh();
    }

    public void preRenderFormEvent() {
        if (clientForm.getClient() == null) {
            initializeClient();
        }
    }

    public void initializeClient() {
        if (clientForm.getClientId() == null) {
            clientForm.setClient(new Client.Builder("", "", null).build());
            return;

        }
        List<Client> clients = database.select(c -> c.getId().intValue() == clientForm.getClientId().intValue());
        if (!clients.isEmpty()) {
            clientForm.setClient(new Client(clients.get(0)));
        }
    }

    public void search() {
        if (searchField != null && searchFieldValue != null && !searchFieldValue.isEmpty()) {
            switch (searchField) {
                case "id":
                    clientsDatatable.setClients(database.select(c -> c.getId().intValue() == Integer.valueOf(searchFieldValue)));
                    break;
                case "firstName":
                    clientsDatatable.setClients(database.select(c -> c.getFirstName().equalsIgnoreCase(searchFieldValue)));
                    break;
                case "lastName":
                    clientsDatatable.setClients(database.select(c -> c.getLastName().equalsIgnoreCase(searchFieldValue)));
                    break;
                case "phone":
                    clientsDatatable.setClients(database.select(c -> c.getPhoneNumber().intValue() == Integer.valueOf(searchFieldValue)));
                    break;
                case "comment":
                    clientsDatatable.setClients(database.select(c -> c.getComment().equalsIgnoreCase(searchFieldValue)));
                    break;
            }
        } else {
            clientsDatatable.refresh();
        }
    }

    public void setDatabase(ClientsDatabase database) {
        this.database = database;
    }

    public void setClientsDatatable(ClientsDatatable clientsDatatable) {
        this.clientsDatatable = clientsDatatable;
    }

    public void setClientForm(ClientForm clientForm) {
        this.clientForm = clientForm;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchFieldValue() {
        return searchFieldValue;
    }

    public void setSearchFieldValue(String searchFieldValue) {
        this.searchFieldValue = searchFieldValue;
    }

}
