/*
 * Created by Jinwoo Yom on 2017.03.28  * 
 * Copyright © 2017 Jinwoo Yom. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import com.mycompany.entityclasses.UserVideo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jinwoo Yom
 */
@Stateless
public class UserVideoFacade extends AbstractFacade<UserVideo> {

    @PersistenceContext(unitName = "Videos-YomPU")
    private EntityManager em;

    // @Override annotation indicates that the super class AbstractFacade's getEntityManager() method is overridden.
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /* 
    This constructor method invokes the parent abstract class AbstractFacade.java's
    constructor method AbstractFacade, which in turn initializes its entityClass instance
    variable with the Photo class object reference returned by the Photo.class.
     */
    public UserVideoFacade() {
        super(UserVideo.class);
    }
    
    // The following method is added to the generated code.
    /**
     * @param userID is the Primary Key of the User entity in a table row in the CloudDriveDB database.
     * @return a list of photos associated with the User whose primary key is userID
     */
    public List<UserVideo> findVideosByUserID(Integer userID) {

        return (List<UserVideo>) em.createNamedQuery("UserPhoto.findVideosByUserID")
                .setParameter("userId", userID)
                .getResultList();
    }
    
    

    /* The following methods are inherited from the parent AbstractFacade class:
    
        create
        edit
        find
        findAll
        remove
     */
    
}


