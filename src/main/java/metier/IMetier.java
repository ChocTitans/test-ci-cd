package metier;

import Model.Credit;

public interface IMetier {
    Credit calculer_Mensualite(Long idCreedit)
        throws Exception;
}
