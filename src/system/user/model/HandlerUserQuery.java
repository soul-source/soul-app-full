package system.user.model;

import hwcore.modules.java.src.library.database.EntityModel;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.common.MyQueryHandler;

public class HandlerUserQuery extends MyQueryHandler {

    public HandlerUserQuery(EntityModel model) {
        super(model, UserRS.class);
    }

    public UserRS loadUtente(int id) {
        return (UserRS) this.loadData(
                getQb().qbBuildName(EntityModelUser.I().ID_UTENTE.getPath()).qbCompare(id).toString()
        ).getRecords().get(0);
    }

    public UserRS loadUtente(String email, String password) {
        try {
            return (UserRS) this.loadData(
                    null,
                    getQb().qbBuildName(EntityModelUser.I().EMAIL.getPath())
                    .qbCompare(email).toString(),
                    getQb().qbBuildName(EntityModelUser.I().PASSWORD.getPath())
                    .qbCompare(password).toString()
            ).getRecords().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean regUser(String name, String lastName, String bornDate,
            String email, String password) {
        EntityModelUser u = EntityModelUser.I();
        QueryBuilder qb = getQb();
        qb.insert()
                .into(u.getTableName())
                .qbAdd("(")
                .qbBuildName(u.NOME.getPath()).qbSep()
                .qbBuildName(u.COGNOME.getPath()).qbSep()
                .qbBuildName(u.DATA_DI_NASCITA.getPath()).qbSep()
                .qbBuildName(u.EMAIL.getPath()).qbSep()
                .qbBuildName(u.PASSWORD.getPath()).qbSep()
                // default
                .qbBuildName(u.CITTA.getPath()).qbSep()
                .qbBuildName(u.STATO.getPath()).qbSep()
                .qbBuildName(u.VIA.getPath()).qbSep()
                .qbBuildName(u.CAP.getPath()).qbSep()
                .qbBuildName(u.COD_FISCALE.getPath())
                .qbAdd(")")
                .values(
                        name,
                        lastName,
                        bornDate,
                        email,
                        password,
                        // empty fields
                        "",
                        "",
                        "",
                        0,
                        ""
                );

        this.execute(qb.toString());

        return true;
    }

    public boolean deleteUser(int id) {
        String query = "DELETE FROM user WHERE id_user= " + id;

        if (this.execute(query) != null) {
            return true;
        }

        return false;
    }

    public boolean updateUser(int id, String password, String name, String lastName, String birthDay,
            String city, String cap, String street, String country, String taxCode) {
        String query = "UPDATE user SET "
                + "password='" + password + "',"
                + "name='" + name + "',"
                + "last_name='" + lastName + "',"
                + "birthdate='" + birthDay + "',"
                + "city='" + city + "',"
                + "tax_code='" + taxCode + "',"
                + "cap='" + cap + "',"
                + "street='" + street + "',"
                + "country='" + country + "' "
                + "WHERE id_user='" + id + "'";

        return this.executeNoRes(query);
    }

    public void updateSession(int uId, String token) {
        String q = "UPDATE user SET session_token ='" + token + "' WHERE id_user='" + uId + "'";
        this.execute(q);
    }
}
