package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;


/**
 * Created by tkagnus on 14/01/2017.
 */
public class HomeController extends Controller{

    /**
     *
     * @return
     */
    public Result index(){

        return ok("buna");

    }

}
