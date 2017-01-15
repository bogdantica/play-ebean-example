package models;

import java.util.*;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.validation.constraints.Pattern;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

import models.Categorie;

/**
 * Computer entity managed by Ebean
 */
@Entity
public class Anunt extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String titlu;

    @Constraints.Required
    public double pret;

    @Constraints.Required
    public String localitate;

    @Constraints.Required
    public String telefon;

//    @Constraints
    public Long afisari;

//    @Constraints.Required
    public String poza;

//    @Constraints.Required
    public String poza2;

    @Constraints.Required
    public String detalii;

    @Formats.DateTime(pattern="yyyy-MM-dd H:i")
    public Date dataora;


//    @ManyToOne
//    public User user;

    @ManyToOne
    public Categorie categorie;

    @ManyToOne
//    @JoinColumn(name="judet")
    public Judet judet;

    @OneToMany(mappedBy = "anunt")
    @OrderBy("dataora DESC")
//    public Comentariu comentarii;
    public List<Comentariu> comentarii = new ArrayList<>();


    @Override
    public void save() {
        this.dataora = new Date();
        this.afisari = 0L;
        super.save();
    }


    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long,Anunt> find = new Finder( Long.class, Anunt.class );
//    public static Find<Long,Anunt> find = new Find<Long,Anunt>(){};

    public static List<Anunt> list(int number){
        return
                find.orderBy("dataora DESC" )
                    .setMaxRows(number)
                    .fetch("comentarii")
                    .findList();

    }


    public static PagedList<Anunt> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .ilike("name", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .fetch("company")
                        .findPagedList(page, pageSize);
    }

}

