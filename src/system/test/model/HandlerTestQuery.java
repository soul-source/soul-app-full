/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.test.model;

import hwcore.modules.java.src.library.database.EntityModel;
import hwcore.modules.java.src.library.database.TableData;
import system.common.MyQueryHandler;
import system.report.model.EntityModelSegnalazione;
import system.user.model.EntityModelUtenteProfile;

public class HandlerTestQuery extends MyQueryHandler {

    public HandlerTestQuery(EntityModel entity) {
        super(entity);
    }

    @Override
    public TableData loadData(String dummy) {
        String query = "";
        if (getModel() instanceof EntityModelUtenteProfile.WithIncarico) {
            query = loadUtenti();
        } else if (getModel() instanceof EntityModelSegnalazione.WithRel) {
            query = loadSegnalazioni();
        }

        return super._loadData(query);
    }

    public String loadUtenti() {
        return "SELECT  Utente.idUtente, Utente.email, Utente.data_di_nascita, Utente.nome,"
                + "Utente.cognome, Utente.codice_fiscale, Utente.via, Utente.citta,"
                + "Utente.cap, Utente.stato, Utente.password, Incarico.Tipo,"
                + "`Puo avere`.Incarico_id, `Puo avere`.Utente_idUtente"
                + " FROM Utente "
                + " INNER  JOIN Incarico"
                + " INNER  JOIN `Puo avere`"
                + " ON `Puo avere`.Incarico_id=Incarico.`ID.Incarico` "
                + " AND `Puo avere`.Utente_idUtente=Utente.idUtente ";
    }

    public String loadSegnalazioni() {
        return "SELECT     Segnalazione.idSegnalazione,"
                + "           Segnalazione.Descrizione,"
                + "           Segnalazione.Numero_commenti,"
                + "           Segnalazione.Data_pubblicazione,"
                + "           Segnalazione.Luogo,"
                + "           Segnalazione.Coordinate,"
                + "           Segnalazione.idSottotipo,"
                + "           Group_concat(Relativo.Numero SEPARATOR ', ') AS numeri,"
                + "           Sottotipo.idSottotipo,"
                + "           Sottotipo.Cause,"
                + "           Sottotipo.Descrizione,"
                + "           Sottotipo.Livello_Priorita,"
                + "           Sottotipo.idtipo,"
                + "           Tipo.`Enti Coinvolte`,"
                + "           Relativo.Numero,"
                + "           Relativo.Sottotipo"
                + " FROM       Segnalazione"
                + " INNER JOIN Sottotipo"
                + " INNER JOIN Tipo"
                + " INNER JOIN Relativo"
                + " ON         Segnalazione.idSottotipo=Sottotipo.idSottotipo"
                + " AND        Sottotipo.idTipo=Tipo.idTipo"
                + " AND        Relativo.Sottotipo=Sottotipo.idSottotipo"
                + " GROUP BY   Segnalazione.idSegnalazione"
                + " ORDER BY   Segnalazione.Data_pubblicazione DESC";
    }

    public void insertCommento() {
        this.execute(
                "INSERT INTO Commento(idCommento,Data_pubblicazione,Messaggio,idUtente,idSegnalazione)"
                + " VALUES(NULL,"
                + "       NULL,"
                + "       'Cosa sta succedendo?',"
                + "       2,"
                + "       4) ;"
                + ""
                + "UPDATE `soul_db`.`Segnalazione`"
                + " SET `soul_db`.`Segnalazione`.`Numero_commenti` = `soul_db`.`Segnalazione`.`Numero_commenti`+1"
                + " WHERE `soul_db`.`Segnalazione`.`idSegnalazione`=4 ;"
        );
    }

    public void deleteComment() {
        this.execute(
                "DELETE FROM Commento WHERE idSegnalazione=4; "
                + "UPDATE Segnalazione SET Numero_commenti=0 WHERE idSegnalazione=4;"
        );
    }

}
