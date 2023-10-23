package presentation;

import metier.IMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class CreditControleur implements ICreditControleur{
   @Autowired
        @Qualifier("metier")
   // CreditControleur Iconroleur;
    //CreditMetier creditMetier;
    IMetier creditMetier;
    public IMetier setCreditMetier(IMetier metier){
        return this.creditMetier=metier;
    }
    @Override
    public void afficher_Menduaalite(Long idCredit) throws Exception {
     var creditAvecMensualite =  creditMetier.calculer_Mensualite(idCredit);
        System.out.println(creditAvecMensualite);
    }
}
