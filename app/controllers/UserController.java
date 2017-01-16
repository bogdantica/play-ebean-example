package controllers;

import com.avaje.ebean.Expr;
import play.data.*;
import static play.data.Form.*;

import models.*;
import play.mvc.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by tkagnus on 16/01/2017.
 */
public class UserController extends Controller {

    private FormFactory formFactory;

    @Inject
    public UserController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    public Result login(){
        return ok(views.html.user.login.render());
    }

    public Result logout(){

        session().clear();
        flash("success","Ai iesit din sistem");

        return Results.redirect(routes.TikController.home());
    }

    public Result auth(){
        String email =  form().bindFromRequest().get("email").toString();
        String parola =  form().bindFromRequest().get("parola").toString();

        User user = User.find
                .where()
                .and(Expr.eq("email",email),Expr.eq("parola",parola))
                .findUnique();

        if(user != null)
        {
            session().clear();
            session("user_id", user.id.toString() );
            session("user_nume", user.nume + " " + user.prenume);

            flash("success","Bun venit, " + user.nume);

            return Results.redirect(routes.TikController.home());

        }
        else{
            flash("error","Contul sau parola nu sunt corecte");

            return badRequest(views.html.user.login.render());
        }
    }

    public Result register(){
        Form<User>  userForm =formFactory.form(User.class);
        return ok(views.html.user.register.render(userForm));
    }

    public Result store(){

        Form<User> user = formFactory.form(User.class).bindFromRequest();
        if(user.hasErrors()){
            return badRequest(views.html.user.register.render(user));
        }
        user.get().save();

        flash("success","Ai fost inregistrat");

        return Results.redirect(routes.UserController.login());
    }

    public Result edit(Long id){

        User userModel = User.find.byId(id);

        Form<User> user = formFactory.form(User.class).fill(userModel);
        return ok(views.html.user.edit.render(user));
    }

    public Result update(){

        Form<User> user = formFactory.form(User.class).bindFromRequest();
        if(user.hasErrors()){
            return badRequest(views.html.user.register.render(user));
        }
        user.get().update();

        flash("success","Modificat cu success");

        return Results.redirect(routes.UserController.edit(user.get().id));
    }

    public Result anunturi(Long user_id){

        List<Anunt> anunturi = Anunt.find
                    .where()
                    .eq("user_id",user_id)
                    .findList();

        return ok(views.html.user.anunturi.render(anunturi));

    }




}
