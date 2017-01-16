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
public class User extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String nume;

    @Constraints.Required
    public String prenume;

    @Constraints.Required
    public String telefon;

    @Constraints.Required
    public String email;

    @Constraints.Required
    public String parola;



    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

}
