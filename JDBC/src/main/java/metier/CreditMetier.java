package metier;

import Model.Credit;
import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("metier")
public class CreditMetier implements IMetier{
    @Autowired
            @Qualifier("dao")
    IDao<Credit,Long> creditDao;
    public IDao<Credit, Long> setCreditDao(IDao<Credit,Long> dao){
     return this.creditDao=dao;
    }


    @Override
    public Credit calculer_Mensualite(Long idCredit) throws Exception {
   var credit = creditDao.trouveParId(idCredit);
   if(credit == null) throw new Exception("L'id du credit est incorrecte :: [Credit non trouve]");
   else{
       double taux = credit.getTaux_Mensuel();
       taux = taux/1200;
       double capitale = credit.getcapilate_Emprunte();
       int nbr_Mois = credit.getnombre_Mois();
       double mensualite =(capitale * taux)/(1 -(Math.pow((1+taux),-1 * nbr_Mois)));
       mensualite = Math.round(mensualite *100)/100;
       credit.setMensualite(mensualite);
       return credit;
   }
    }

}
