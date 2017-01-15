package models;

import java.util.*;
import javax.persistence.*;
import javax.persistence.OrderBy;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

import models.Categorie;

/**
 * Computer entity managed by Ebean
 */
@Entity
public class Comentariu extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String comentariu;

    //    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date dataora;


    @ManyToOne
    @JoinColumn(name = "anunt_id")
    public Anunt anunt;

    public Long user_id;


    @Override
    public void save() {
        this.dataora = new Date();

        super.save();
    }


//    public static Finder<Long,Comentariu> find = new Finder( Long.class, Comentariu.class );


}

