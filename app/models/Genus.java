package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Company entity managed by Ebean
 */
@Entity 
public class Genus extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required
    public String scientificName;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Genus> find = new Model.Finder<Long,Genus>(Long.class, Genus.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Genus c: Genus.find.orderBy("scientificName").findList()) {
            options.put(c.id.toString(), c.scientificName);
        }
        return options;
    }

}

