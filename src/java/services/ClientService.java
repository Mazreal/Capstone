/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.ClientJDBC;
import domain.Client;
import java.util.ArrayList;

/**
 *
 * @author 673970
 */
public class ClientService
{
    private ClientJDBC clientJDBC;
    public ClientService()
    {
        clientJDBC = new ClientJDBC();
    }
    
    public int insertClient(Client client)
    {
        return clientJDBC.insertClient(client);
    }
    
    public int updateClient(Client client)
    {
        return clientJDBC.updateClient(client);
    }
    public int deleteClient(int clientNo)
    {
        return clientJDBC.deleteClient(clientNo);
    }
    
    public Client getClient(int clientNo)
    {
        return clientJDBC.getClient(clientNo);
    }
    
    public ArrayList<Client> getAllClients()
    {
        return clientJDBC.getAllClients();
    }
    
    public int findHighestClientId()
    {
        return clientJDBC.findHighestClientId();
    }
}
