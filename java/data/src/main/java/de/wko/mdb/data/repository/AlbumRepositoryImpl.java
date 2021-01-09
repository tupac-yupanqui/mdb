package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.filter.AlbumFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AlbumEntity> findFilteredAlbums(AlbumFilter filter) {

        String sql = "select a.* from albums a, artists i where a.artist_id=i.id ";//order by i.name desc,a.name;";
        if (!StringUtils.isEmpty(filter.getAlbum())) {
            sql += " and a.name like '%"+filter.getAlbum()+"%'";
        }
        if (!StringUtils.isEmpty(filter.getArtist())) {
            sql += " and i.name like '%"+filter.getArtist()+"%'";
        }
        if (!StringUtils.isEmpty(filter.getYear())) {
            sql += " and year(a.release)="+filter.getYear();
        }
        if (filter.getSorttype()==null) {
            sql += " order by i.name, a.name";
        } else {
            switch (filter.getSorttype()) {
                case ARTIST:
                    sql += " order by i.name "+filter.getSortorder()+", a.name";
                    break;
                case ALBUM:
                    sql += " order by a.name "+filter.getSortorder()+", i.name";
                    break;
                case YEAR:
                    sql += " order by a.release "+filter.getSortorder();
                    break;
            }
        }
        if (filter.getAmount()>0) {
            sql += " limit "+ filter.getStart()+","+filter.getAmount();
        }
        Query query = em.createNativeQuery(sql, AlbumEntity.class);

        return query.getResultList();
    }
}
