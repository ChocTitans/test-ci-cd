package dao.Mysql;

import Model.Credit;
import Model.Utilisateur;
import dao.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CreditDao implements IDao<Credit, Long> {

    MysqlSessionFactory factory;


    @Override
    public Credit trouveParId(Long idCredit) {
        Credit credit = null;
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String SQL = "SELECT * FROM credit where id = ?";

        try {
            ps = Utilitaire.initPS(session, SQL, false, idCredit);
            rs = ps.executeQuery();
            if(rs.next()) credit = map(rs);
            System.out.println("[SQL] : " + SQL);
            return credit;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps, rs);
        }
        return credit;
    }

    @Override
    public List<Credit> findAll() {
        List<Credit> credits = null;
        Connection session = factory.getSession();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String SQL = "SELECT * FROM credit";

        try {
            ps = Utilitaire.initPS(session, SQL, false);
            rs = ps.executeQuery();
            if(rs.next()) credits.add(map(rs));
            System.out.println("[SQL] : " + SQL);
            return credits;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps, rs);
        }
        return credits;
    }

    @Override
    public Credit save(Credit credit) {
        Connection session = factory.getSession();
        PreparedStatement ps = null;

        String SQL = "INSERT INTO credit(capital, nbrMois, taux, demandeur, mensualite VALUES (?,?,?,?,?)";
        try {

            ps = Utilitaire.initPS(session,SQL,true,credit.getcapilate_Emprunte(),credit.getnombre_Mois(),credit.getTaux_Mensuel(),
                    credit.getNom_Demandeur().getId(), credit.getmensualite());

            var statut = ps.executeUpdate();
            if(statut == 0) System.out.println("0 Crédit inséré");
            else
            {
                var rs = ps.getGeneratedKeys();
                var id = rs.getLong(1);
                credit.setId(id);
            }
            System.out.println("[SQL] : " + SQL);
            return credit;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps);
        }

        return credit;
    }

    @Override
    public Credit update(Credit credit) {
        Connection session = factory.getSession();
        PreparedStatement ps = null;

        String SQL = "UPDATE credit set capital = ?, nbrMois = ?, taux = ?, demandeur = ?, mensualite = ? WHERE id = ?";
        try {

            ps = Utilitaire.initPS(session,SQL,true,credit.getcapilate_Emprunte(),credit.getnombre_Mois(),credit.getTaux_Mensuel(),
                    credit.getNom_Demandeur().getId(), credit.getmensualite());

            var statut = ps.executeUpdate();
            if(statut == 0) System.out.println("0 Crédit modifié !");
            System.out.println("[SQL] : " + SQL);
            return credit;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps);
        }

        return credit;    }

    @Override
    public Boolean delete(Credit credit) {
        Connection session = factory.getSession();
        PreparedStatement ps = null;

        String SQL = "DELETE FROM creditr WHERE id = ?";
        try {

            ps = Utilitaire.initPS(session,SQL,true,credit.getId());

            var statut = ps.executeUpdate();
            if(statut == 0) System.out.println("0 Crédit supprimé");

            System.out.println("[SQL] : " + SQL);
            return true;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            Utilitaire.close(ps);
        }


        return null;
    }

    @Override
    public Boolean deteleByID(Long aLong) {
        return null;
    }

    public Credit map(ResultSet rs)
    {
        try {
            var id = rs.getLong("id");
            var capital = rs.getDouble("capital");
            var nbrMois = rs.getInt("nbrMois");
            var taux = rs.getDouble("taux");
            var idClient = rs.getInt("demandeur");
            var mensualite = rs.getDouble("mensualite");


            var client = factory.getClientDao().trouveParId((long)idClient);

            return new Credit(id, capital, nbrMois, taux, client, mensualite);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
