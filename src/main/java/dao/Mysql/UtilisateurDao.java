package dao.Mysql;

import Model.Utilisateur;
import dao.IDao;

import java.util.List;

public class UtilisateurDao implements IDao<Utilisateur, Long> {
    @Override
    public Utilisateur trouveParId(Long aLong) {
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        return null;
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Boolean delete(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Boolean deteleByID(Long aLong) {
        return null;
    }
}
