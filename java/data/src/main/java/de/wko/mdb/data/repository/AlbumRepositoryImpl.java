package de.wko.mdb.data.repository;

import de.wko.mdb.types.util.Util;
import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.types.ScoredAlbum;
import de.wko.mdb.types.query.SearchAlbumBlurQuery;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public AlbumEntity getAlbum(Long id) {
        String sql = "select * from albums where id="+id;
        System.out.println(sql);
        Query query = em.createNativeQuery(sql, AlbumEntity.class);
        return (AlbumEntity)query.getSingleResult();
    }

    @Override
    public List<AlbumEntity> findFilteredAlbums(AlbumFilter filter) {

        String sql = "select a.* "+getFilterdQuery(filter);
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
    @Override
    public Long countFilteredAlbums(AlbumFilter filter) {

        String sql = "select count(*) "+getFilterdQuery(filter);

        Query query = em.createNativeQuery(sql);
        System.out.println(sql);
        return ((BigInteger)query.getSingleResult()).longValue();
    }

    private String getFilterdQuery(AlbumFilter filter) {
        String sql = " from albums a, artists i where a.artist_id=i.id ";//order by i.name desc,a.name;";
        if (!StringUtils.isEmpty(filter.getAlbum())) {
            sql += " and a.name like '%"+filter.getAlbum()+"%'";
        }
        if (!StringUtils.isEmpty(filter.getArtist())) {
            sql += " and i.name like '%"+filter.getArtist()+"%'";
        }
        if (!StringUtils.isEmpty(filter.getYear())) {
            sql += " and year(a.release)="+filter.getYear();
        }
        return sql;
    }

    @Override
    public List<ScoredAlbum> searchAlbumBlur(SearchAlbumBlurQuery searchQuery) {
        String sql = "select a.*, %s as score from albums a %s order by score %s;";

        String levenshtein = String.format("levenshtein(a.name, '%s')", Util.mask(searchQuery.getAlbum()));
        sql = String.format(sql,
                levenshtein,
                searchQuery.getScoreMax()>0 ? "where "+levenshtein+"<"+searchQuery.getScoreMax():"",
                searchQuery.getScoreCount()>0 ? "limit "+searchQuery.getScoreCount():""
        );
        Query q = em.createNativeQuery(sql, "BlurAlbumResult");
        List<Object[]> rl = q.getResultList();

        List<ScoredAlbum> result = new ArrayList<>();
        for (Object[] record : rl) {
            ScoredAlbum scoredAlbum = new ScoredAlbum();
            scoredAlbum.setAlbum(((AlbumEntity)record[0]).getType());
            scoredAlbum.setScore((Integer)record[1]);
            result.add(scoredAlbum);
        }

        return result;
    }


}
