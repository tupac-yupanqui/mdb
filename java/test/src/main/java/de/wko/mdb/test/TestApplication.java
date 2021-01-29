package de.wko.mdb.test;

import de.wko.mdb.data.repository.AlbumRepository;
import de.wko.mdb.bl.service.AlbumService;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Subalbum;
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

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			System.out.println("Running demo");
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
		};
	}

}
