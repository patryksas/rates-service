package com.demo.model.dao;

import com.demo.model.entity.Rate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class RateDao {

    @PersistenceContext(unitName = "restapi_PU")
    EntityManager em;


    public void save(Rate rate) {
        em.persist(rate);
    }

    public List<Rate> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rate> cq = cb.createQuery(Rate.class);
        Root<Rate> root = cq.from(Rate.class);
        cq.orderBy(cb.asc(root.get("dateOfRate")));
        return em.createQuery(cq).getResultList();
    }
}
