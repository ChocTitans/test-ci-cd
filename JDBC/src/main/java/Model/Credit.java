package Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @NoArgsConstructor
public class Credit {
    private Long id;
    private Double capilate_Emprunte;
    private Integer nombre_Mois;
    private Double taux_Mensuel;
    private Double mensualite;
    private Client nom_Demandeur;

    public Credit(long id, Client nom_Demandeur) {
        this.id=id;
        this.nom_Demandeur = nom_Demandeur;
    }



    public Double setMensualite(Double mensualite){
       return this.mensualite=mensualite;
    }
  public   Credit(Long id,Double capilate_Emprunte,Integer nombre_Mois,Double taux_Mensuel,Client nom_Demandeur,Double mensualite){
      this.id=id;
      this.capilate_Emprunte=capilate_Emprunte;
      this.nombre_Mois=nombre_Mois;
      this.taux_Mensuel=taux_Mensuel;
      this.nom_Demandeur=nom_Demandeur;
      this.mensualite=mensualite;

  }
  public Long getId(){
      return id;
  }
    public Double getTaux_Mensuel(){
      return taux_Mensuel;

  }
    public Integer getnombre_Mois(){
      return nombre_Mois;

     }
    public  Double getcapilate_Emprunte(){
      return capilate_Emprunte;
     }

    public Client getNom_Demandeur() {
        return nom_Demandeur;
    }

    public  Double getmensualite(){
      return mensualite;
     }



    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", capilate_Emprunte=" + capilate_Emprunte +
                ", nombre_Mois=" + nombre_Mois +
                ", taux_Mensuel=" + taux_Mensuel +
                ", nom_Demandeur='" + nom_Demandeur.nomComplet() + '\'' +
                ", mensualite=" + mensualite +
                '}';
    }

}
