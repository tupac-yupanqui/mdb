package de.wko.mdb.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class SubalbumRepositoryImpl implements SubalbumRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

}
