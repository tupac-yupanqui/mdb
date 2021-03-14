package de.wko.mdb.data.repository;

import de.wko.mdb.types.util.Util;
import de.wko.mdb.data.entity.ArtistEntity;
import de.wko.mdb.types.ScoredArtist;
import de.wko.mdb.types.query.SearchArtistBlurQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ScoredArtist> searchArtistBlur(SearchArtistBlurQuery searchQuery) {
        String sql = "select a.*, %s as score from artists a %s order by score %s;";

        String levenshtein = "levenshtein(a.name, '"+ Util.mask(searchQuery.getArtist())+"')";
        sql = String.format(sql,
                levenshtein,
                searchQuery.getScoreMax()>0 ? "where "+levenshtein+"<"+searchQuery.getScoreMax():"",
                searchQuery.getScoreCount()>0 ? "limit "+searchQuery.getScoreCount():""
        );
        Query q = em.createNativeQuery(sql, "BlurArtistResult");
        List<Object[]> rl = q.getResultList();

        List<ScoredArtist> result = new ArrayList<>();
        for (Object[] record : rl) {
            ScoredArtist scoredartist = new ScoredArtist();
            scoredartist.setArtist(((ArtistEntity)record[0]).getType());
            scoredartist.setScore((Integer)record[1]);
            result.add(scoredartist);
        }

        return result;
    }

}
