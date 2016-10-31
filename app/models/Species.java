package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Computer entity managed by Ebean
 */
@Entity 
public class Species extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required
    public String localName;
    
    @Constraints.Required
    public String scientificName;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date documented;
    
    @Constraints.Required
    public Boolean canFly;
    
    @Constraints.Required
    public Boolean isExtinct;

    @ManyToOne
    public Genus genus;
    
    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long,Species> find = new Finder<Long,Species>(Long.class, Species.class); 
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Species> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("species")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }
    
}
