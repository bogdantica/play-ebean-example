package controllers;

import ch.qos.logback.core.util.FileUtil;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Transaction;
import org.h2.store.fs.FileUtils;
import org.springframework.util.FileCopyUtils;
import play.api.Play;
import play.api.mvc.MultipartFormData;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.api.Play.*;
import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.io.File;
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
        if(anunt.afisari != null) {
            anunt.afisari = anunt.afisari + 1;
        }else{
            anunt.afisari = 0L;
        }
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

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> poza_temp = body.getFile("poza");
        Http.MultipartFormData.FilePart<File> poza_temp2 = body.getFile("poza2");

        File poza = poza_temp.getFile();
        File poza2 = poza_temp2.getFile();

        String path = "/Work/dai-play/play-ebean-example/public/img/";

        Anunt anuntModel = anuntForm.get();

        if(poza.renameTo(new File(path,poza_temp.getFilename()))){
            anuntModel.setPoza(poza.getAbsolutePath());
            anuntModel.poza = poza_temp.getFilename();
        }else{
            anuntModel.poza = "";
        }
        if(poza2.renameTo(new File(path,poza_temp2.getFilename()))){
            anuntModel.poza2 = poza2.getAbsolutePath();
            anuntModel.poza = poza_temp2.getFilename();
        }else{
            anuntModel.poza2 = "";
        }
        anuntModel.save();
        flash("success","Anuntul a fost adaugat cu success");

        return Results.redirect(routes.TikController.home());
    }
    public Result update(){

        Form<Anunt> anuntForm = formFactory.form(Anunt.class).bindFromRequest();

        if(anuntForm.hasErrors()){
            return badRequest(views.html.anunt.adauga.render(anuntForm));

        }

        anuntForm.get().update();

        flash("success","Anuntul a fost modificat");

        return Results.redirect(routes.TikController.home());
    }

    public Result edit(Long id){

        Anunt anunt = Anunt.find.byId(id);

        Form<Anunt> anuntForm = formFactory.form(Anunt.class).fill(anunt);

        return ok(views.html.anunt.edit.render(anuntForm,anunt.id));
    }


}
