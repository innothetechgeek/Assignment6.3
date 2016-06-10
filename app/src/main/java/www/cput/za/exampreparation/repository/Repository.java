package www.cput.za.exampreparation.repository;

import java.util.Set;

/**
 * Created by Game330 on 2016-05-31.
 */
public interface Repository <E, ID> {

        E findById(ID id);

        E save(E entity);

        E update(E entity);

        E delete(E entity);

        Set<E> findAll();

        int deleteAll();


}
