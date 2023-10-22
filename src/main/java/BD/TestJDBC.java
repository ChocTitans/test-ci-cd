package BD;

import Model.Client;
import Model.Credit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestJDBC {
    public static void main(String[] args) {

        Connection connection = Singleton.getSession();
        var crédits = new ArrayList<Credit>();
        try {

            var ps = connection.prepareStatement("SELECT cr.id, cr.capital, cr.nbrMois, cr.taux, cr.demandeur,cr.mensualite," +
                    "u.nom, u.prenom from client cl, credit cr, utilisateur u");

            var rs = ps.executeQuery();

            while(rs.next())
            {
                var id = rs.getLong("id");
                var capital = rs.getDouble("capital");
                var nbrMois = rs.getInt("nbrMois");
                var taux = rs.getDouble("taux");
                var nomd = rs.getString("nom");
                var prenomd = rs.getString("prenom");
                var mensualite = rs.getDouble("mensualite");

                var client = new Client();
                client.setNomcomplet(nomd,prenomd);
                crédits.add(new Credit(id, capital, nbrMois, taux, client, mensualite));

            }

            if(crédits.isEmpty()) throw new SQLException("Aucun crédit trouvé");
            else crédits.forEach(System.out::println);


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        Singleton.closeSession();

    }
}
