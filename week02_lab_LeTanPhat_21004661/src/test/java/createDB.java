import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import javax.swing.text.html.parser.Entity;

public class createDB {
    public static void main(){
        EntityManager em = Persistence.createEntityManagerFactory("MariaDB").createEntityManager();
        em.close();
    }
}
