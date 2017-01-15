package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Constraint;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Created by tkagnus on 15/01/2017.
 */
@Entity
public class Categorie extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String nume;

    public static Find<Long,Categorie> find = new Find<Long,Categorie>(){};

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Categorie c: Categorie.find.orderBy("nume").findList()) {
            options.put(c.id.toString(), c.nume);
        }
        return options;
    }

}
