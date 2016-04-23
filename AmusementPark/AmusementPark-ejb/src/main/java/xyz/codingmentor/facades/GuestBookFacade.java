package xyz.codingmentor.facades;

import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;
import javax.ejb.Singleton;
import javax.persistence.Query;
import xyz.codingmentor.entities.GuestBook;

@Singleton
public class GuestBookFacade extends EntityFacade {

    private static final Logger LOG = Logger.getLogger(GuestBookFacade.class.getName());

    public GuestBookFacade() {
        //it is bean
    }

    public List<GuestBook> findAllGuestBook() {
        Query query = entityManager.createNamedQuery("GuestBook.findAll");
        return query.getResultList();
    }

    public void addGuestBook(GuestBook guestBook) {
        create(guestBook);
    }

    public GuestBook getGuestBook(Long idOfGuestBook) throws NotFoundException {
        GuestBook guestBook = entityManager.find(GuestBook.class, idOfGuestBook);
        if (null == guestBook) {
            throw new NotFoundException("GuestBook doesn't exit in DB.");
        }
        return guestBook;
    }

//    public boolean existGuestBookInDB(Long idOfGuestBook) {
//        try {
//            entityManager.getReference(GuestBook.class, idOfGuestBook);
//        } catch (EntityNotFoundException ex) {
//            LOG.info("GuestBook doesn't exit in DB.");
//            return false;
//        }
//        return true;
//    }

    public void update(GuestBook guestBook) {
        merge(guestBook);
    }

    public void remove(GuestBook guestBook) {
        delete(guestBook);
    }

}
