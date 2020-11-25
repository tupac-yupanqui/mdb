package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AlbumCustomRepositoryImpl implements AlbumCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AlbumEntity> findByArtistId(Long artistId) {
        String sql = "select * from albums where artist_id="+artistId;
        Query query = em.createQuery(sql);
        return query.getResultList();
    }

}