package controllers;

import play.data.*;
import static play.data.Form.*;

import models.*;
import play.mvc.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 * Created by tkagnus on 15/01/2017.
 */
public class TikController extends Controller {


    public Result home(){

//        return ok(views.html.index.render(Anunt.list(6)));
        return ok(
                views.html.index.render(
                        Anunt.list(6)
                )
        );
    }

}
