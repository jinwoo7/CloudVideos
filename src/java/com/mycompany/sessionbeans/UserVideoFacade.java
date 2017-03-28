/*
 * Created by Jinwoo Yom on 2017.03.28  * 
 * Copyright © 2017 Jinwoo Yom. All rights reserved. * 
 */
package com.mycompany.sessionbeans;

import com.mycompany.entityclasses.UserVideo;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserVideoFacade() {
        super(UserVideo.class);
    }
    
}
