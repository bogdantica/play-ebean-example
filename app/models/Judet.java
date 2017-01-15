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
public class Judet extends Model {

    @Id
    public Long id;

    public String code;
    public String judet;

    public static Finder<Long, Judet> find = new Finder<Long,Judet>(Judet.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Judet c: Judet.find.orderBy("judet").findList()) {
            options.put(c.id.toString(), c.judet);
        }
        return options;
    }

}
