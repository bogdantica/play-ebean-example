package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 * Created by tkagnus on 15/01/2017.
 */
public class ComentariuController extends Controller {

    private FormFactory formFactory;

    @Inject
    public ComentariuController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    public Result adauga(Long anunt_id){
        Form<Comentariu> comentariuForm = formFactory.form(Comentariu.class).bindFromRequest();

        if(comentariuForm.hasErrors()){
            Anunt anunt = Anunt.find.byId(anunt_id);
            return badRequest(views.html.anunt.anunt.render(anunt,comentariuForm));
        }

        comentariuForm.get().save();
        flash("success","Comentariu adaugat cu success");

        return Results.redirect(routes.AnuntController.anunt(anunt_id));
    }
}
