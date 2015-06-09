/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.user.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;

/**
 *
 *
 */
public class EntityModelUtente extends EntityModelUtenteProfile {

    public FieldModel LIVELLO_UTENTE,
            NUM_NOTIFICHE_NON_LETTE;

    protected EntityModelUtente() {
        super();

        this.createFields(
            LIVELLO_UTENTE = new VisualName("user_level", "Livello", this),
            NUM_NOTIFICHE_NON_LETTE = new VisualName("unread_notifications_number", "N° notifiche non lette", this)
        );
    }
    
    public static EntityModelUtente I() {
        return I(EntityModelUtente.class);
    }
}
