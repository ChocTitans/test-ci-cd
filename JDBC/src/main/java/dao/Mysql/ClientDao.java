package dao.Mysql;

import Model.Client;
import dao.IDao;

import java.util.List;

public class ClientDao implements IDao<Client, Long> {

    @Override
    public Client trouveParId(Long aLong) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public Boolean delete(Client client) {
        return null;
    }

    @Override
    public Boolean deteleByID(Long aLong) {
        return null;
    }
}
