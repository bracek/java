package com.globallogic.kaacoo.view.presidentList;

import com.globallogic.kaacoo.model.President;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Miroslav Katrak
 */

@Component
public class PresidentListJpaDao {

    @PersistenceContext
    private EntityManager em;

    public List<President> findPresidents() {
        return em.createQuery("select m from President as m order by m.firstName", President.class)
                .getResultList();
    }
}
