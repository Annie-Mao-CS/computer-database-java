package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;

import models.*;

/**
 * Manage a database of speciess
 */
public class Application extends Controller {
    
    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.list(0, "scientificName", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to speciess list
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * Display the paginated list of speciess.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on species names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Species.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Species.
     *
     * @param id Id of the species to edit
     */
    public static Result edit(Long id) {
        Form<Species> speciesForm = form(Species.class).fill(
            Species.find.byId(id)
        );
        return ok(
            editForm.render(id, speciesForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the species to edit
     */
    public static Result update(Long id) {
        Form<Species> speciesForm = form(Species.class).bindFromRequest();
        if(speciesForm.hasErrors()) {
            return badRequest(editForm.render(id, speciesForm));
        }
        speciesForm.get().update(id);
        flash("success", "Species " + speciesForm.get().scientificName + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new species form'.
     */
    public static Result create() {
        Form<Species> speciesForm = form(Species.class);
        return ok(
            createForm.render(speciesForm)
        );
    }
    
    /**
     * Handle the 'new species form' submission 
     */
    public static Result save() {
        Form<Species> speciesForm = form(Species.class).bindFromRequest();
        if(speciesForm.hasErrors()) {
            return badRequest(createForm.render(speciesForm));
        }
        speciesForm.get().save();
        flash("success", "Species " + speciesForm.get().scientificName + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle species deletion
     */
    public static Result delete(Long id) {
        Species.find.ref(id).delete();
        flash("success", "Species has been deleted");
        return GO_HOME;
    }
    

}
            
