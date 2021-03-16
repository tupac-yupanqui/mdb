package de.wko.mdb.test;

import de.wko.mdb.bl.service.ContentService;
import de.wko.mdb.bl.service.AlbumService;
import de.wko.mdb.bl.service.SubalbumService;
import de.wko.mdb.bl.service.TitelService;
import de.wko.mdb.data.entity.SubalbumEntity;
import de.wko.mdb.data.repository.SubalbumRepository;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.ScoredArtist;
import de.wko.mdb.types.Subalbum;
import de.wko.mdb.types.Titel;
import de.wko.mdb.types.query.SearchArtistBlurQuery;
import de.wko.mdb.types.query.SearchTitelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"de.wko.mdb"})
public class TestApplication {

	@Autowired
	AlbumService albumService;

	@Autowired
	SubalbumRepository subalbumRepository;

	@Autowired
	ContentService contentService;

	@Autowired
	TitelService titelService;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			System.out.println("Running demo");

			SearchTitelQuery query = new SearchTitelQuery();
			query.setTitel("Storytime");
			query.setArtist("Nightwish");

			List<Titel> tl = contentService.searchTitel(query);
			for (Titel t : tl) {
				System.out.println("Titel: "+t.getName());
			}
/*
			List<SubalbumEntity> list = subalbumRepository.findTitellistByAlbumId(132L);
			System.out.println("####### Size = "+list.size());
			if (list.size()>0) {
				System.out.println("####### ID = "+list.get(0).getId());
			}
*/
			/*
			Album al = albumService.getById(1L);
			System.out.println("Album Name: "+al.getName());
			System.out.println("ID Name: "+al.getId());

			Subalbum sa = albumService.getSubalbumById(1L);
			System.out.println("SubalbumName: "+sa.getName());
			System.out.println("ParentId: "+sa.getParentId());
			 */
/*
			SearchArtistBlurQuery r = new SearchArtistBlurQuery();
			r.setArtist("Beast black");
			r.setScoreMax(10);
			r.setScoreCount(3);
			List<ScoredArtist> list = contentService.searchArtistBlur(r);

			if (list==null) {
				System.out.println("Result is null");
			} else {
				System.out.println("Result count: "+list.size());
				for (ScoredArtist st : list) {
					System.out.println(String.format("%s (%d)",  st.getArtist().getName(), st.getScore()));
				}
			}
*/
			/*
			SearchTitelBlurQuery r = new SearchTitelBlurQuery();
			r.setTitel("Elvenpath");
			//r.setArtist("Nightwish");
			r.setScoreMax(10);
			r.setScoreCount(3);
			r.setAlbumId(1L);
			List<ScoredTitel> list = contentService.searchTitelBlur(r);

			if (list==null) {
				System.out.println("Result is null");
			} else {
				System.out.println("Result count: "+list.size());
				for (ScoredTitel st : list) {
					System.out.println(String.format("%s - %s (%d)", st.getTitel().getArtist().getName(), st.getTitel().getName(), st.getScore()));
				}
			}
*/
			/*
			Album a = albumService.getById(1L);
			System.out.println("Album: "+a.getName());
			System.out.println("Subs: "+a.getSubalbums().size());
			for (Subalbum sa : a.getSubalbums()) {
				System.out.println("     "+sa.getName());
			}
			List<Album> list = albumService.getByArtistId(1L);
			for (Album a2 : list) {
				System.out.println("Album: "+a2.getName());
				System.out.println("  Subalbums: "+a2.getSubalbums().size());
			}
			List<Album> list3 = albumService.getByArtist(1L);
			for (Album a3 : list3) {
				System.out.println("Album: "+a3.getName());
				System.out.println("  Subalbums: "+a3.getSubalbums().size());
			}
			System.out.println("Anzahl: "+albumService.getCount());

			 */
		};
	}

}
