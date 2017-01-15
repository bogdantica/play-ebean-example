package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by tkagnus on 15/01/2017.
 */
public class AnuntController extends Controller {

    private FormFactory formFactory;

    @Inject
    public AnuntController(FormFactory formFactory){
        this.formFactory = formFactory;
    }


    public Result cauta(){
        String key = form().bindFromRequest().get("key").toString();
        Long judet_id = Long.parseLong(form().bindFromRequest().get("judet_id"));
        List<Anunt> anunturi = Anunt.find
                .where()
                .or(Expr.ilike("titlu","%" + key + "%" ), Expr.ilike("detalii", "%" + key + "%"))
                .eq("judet_id",judet_id)
                .findList();

        if(anunturi.isEmpty())
        {
            flash("success","nu s-au gasit anunturi");
            return Results.redirect(routes.TikController.home());
        }

        return ok(views.html.anunt.cauta.render(anunturi));
    }

    public Result anunt(Long id){

        Anunt anunt = Anunt.find.byId(id);

        anunt.afisari = anunt.afisari + 1;
        anunt.update();

        Form<Comentariu> comentariuForm = formFactory.form(Comentariu.class);
        return ok(views.html.anunt.anunt.render(anunt,comentariuForm));
    }

    public Result adauga(){
        Form<Anunt> anuntForm = formFactory.form(Anunt.class);

        return ok(views.html.anunt.adauga.render(anuntForm));
    }

    public Result salveaza(){

        Form<Anunt> anuntForm = formFactory.form(Anunt.class).bindFromRequest();

        if(anuntForm.hasErrors()){
            return badRequest(views.html.anunt.adauga.render(anuntForm));

        }

        anuntForm.get().save();

        flash("success","Anuntul a fost adaugat cu success");

        return Results.redirect(routes.TikController.home());
    }



}
