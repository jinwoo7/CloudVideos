/*
 * Created by Jinwoo Yom on 2017.03.28  * 
 * Copyright © 2017 Jinwoo Yom. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import com.mycompany.entityclasses.PublicVideo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jinwoo Yom
 */
@Stateless
public class PublicVideoFacade extends AbstractFacade<PublicVideo> {

    @PersistenceContext(unitName = "Videos-YomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicVideoFacade() {
        super(PublicVideo.class);
    }
    
    /*
    ---------------------------
    Search Category: Title
    ---------------------------
    */
    public List<PublicVideo> titleQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the State name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT c FROM PublicVideo c WHERE c.title LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
    
    /*
    ---------------------------
    Search Category: Description
    ---------------------------
    */
    public List<PublicVideo> descriptionQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the State name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT c FROM PublicVideo c WHERE c.description LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
    
    /*
    ---------------------------
    Search Category: Category
    ---------------------------
    */
    public List<PublicVideo> categoryQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the State name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT c FROM PublicVideo c WHERE c.category LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
    
    /*
    --------------------
    Search Category: ALL
    --------------------
     */
    public List<PublicVideo> allQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery("SELECT c FROM PublicVideo c WHERE c.title LIKE :searchString OR c.description LIKE :searchString OR c.category LIKE :searchString").setParameter("searchString", searchString).getResultList();
    }
}
