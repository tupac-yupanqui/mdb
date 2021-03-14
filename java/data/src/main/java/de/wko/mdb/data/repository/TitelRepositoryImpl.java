package de.wko.mdb.data.repository;

import de.wko.mdb.types.util.Util;
import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.entity.TitelEntity;
import de.wko.mdb.types.ScoredTitel;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TitelRepositoryImpl implements TitelRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public AlbumEntity getAlbum(int id) {
        String sql = "select * from albums where id="+id;
        System.out.println(sql);
        Query query = em.createNativeQuery(sql, AlbumEntity.class);
        return (AlbumEntity)query.getSingleResult();
    }

    @Override
    public List<ScoredTitel> searchTitelBlur(SearchTitelBlurQuery searchQuery) {
        String sql = "select t.*, %s as score from titels t, artists a where t.artist_id=a.id %s %s order by score %s;";

        String levenshtein;
        if (StringUtils.isEmpty(searchQuery.getArtist())) {
            levenshtein = "levenshtein(t.name, '"+ Util.mask(searchQuery.getTitel())+"')";
        } else {
            levenshtein = "levenshtein(concat(t.name,a.name), '"+Util.mask(searchQuery.getTitel()+searchQuery.getArtist())+"')";
        }
        sql = String.format(sql,
                levenshtein,
                searchQuery.getAlbumId()>0 ? "and t.album_id="+searchQuery.getAlbumId():"",
                searchQuery.getScoreMax()>0 ? "and "+levenshtein+"<"+searchQuery.getScoreMax():"",
                searchQuery.getScoreCount()>0 ? "limit "+searchQuery.getScoreCount():""
        );

        Query q = em.createNativeQuery(sql, "BlurTitelResult");
        List<Object[]> rl = q.getResultList();

        List<ScoredTitel> result = new ArrayList<>();
        for (Object[] record : rl) {
            ScoredTitel scoredTitel = new ScoredTitel();
            scoredTitel.setTitel(((TitelEntity)record[0]).getType());
            scoredTitel.setScore((Integer)record[1]);
            result.add(scoredTitel);
        }

        return result;
    }
}
