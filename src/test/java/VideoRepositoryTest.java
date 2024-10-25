
import java.util.List;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.Impl.VideoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import culturemedia.model.Video;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.service.CulturemediaService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoRepositoryTest {

	private VideoRepository videoRepository;
	private CulturemediaService culturemediaService;

	@BeforeEach
	void init(){
		videoRepository = new VideoRepositoryImpl();

		// Agregar videos al repositorio en el metodo init:
		List<Video> videos = List.of(new Video("01", "Título 1", "----", 4.5),
										   new Video("02", "Título 2", "----", 5.5),
										   new Video("03", "Título 3", "----", 4.4),
										   new Video("04", "Título 4", "----", 3.5),
										   new Video("05", "Clic 5", "----", 5.7),
									 	   new Video("06", "Clic 6", "----", 5.1));

		// Guardar los videos en el repositorio
		for ( Video video : videos ) {
			videoRepository.save( video );
		}

	}

	@Test
	void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
		List<Video> videos = videoRepository.findAll( );
		assertEquals(6, videos.size());
	}

	@Test
	void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_successfully() {
		List<Video> videos = videoRepository.find( "Clic" );
		assertEquals(2, videos.size());
	}

	@Test
	void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() {
		List<Video> videos = videoRepository.find( 4.5, 5.5 );
		assertEquals(3, videos.size());
	}

	@Test
	void when_FindByTitle_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
		List<Video> videos = videoRepository.find("Non-existent Title");
		assertTrue(videos.isEmpty(), "Se espera una lista vacía cuando no hay coincidencias en el título.");
	}

	@Test
	void when_FindByDuration_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
		List<Video> videos = videoRepository.find(10.0, 12.0); // Rango sin coincidencias
		assertTrue(videos.isEmpty(), "Se espera una lista vacía cuando no hay videos en el rango de duración especificado.");
	}

	@Test
	void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
		// Limpiar el repositorio para probar la excepción
		videoRepository = new VideoRepositoryImpl(); // Repositorio vacío
		assertThrows(VideoNotFoundException.class, () -> videoRepository.findAll(),
				"Se esperaba VideoNotFoundException cuando no hay videos en el repositorio.");
	}


	// Casos de prueba para el servicio CulturemediaService

	@Test
	void when_FindAllVideos_should_return_all_videos() throws VideoNotFoundException {
		List<Video> videos = culturemediaService.findAllVideos();
		assertEquals(6, videos.size(), "Se esperaba que se devolvieran todos los videos.");
	}

	@Test
	void when_FindAllVideos_should_throw_VideoNotFoundException_when_no_videos_found() {
		videoRepository = new VideoRepositoryImpl(); // Crear un repositorio vacío
		culturemediaService = new CulturemediaService(videoRepository);

		assertThrows(VideoNotFoundException.class, () -> culturemediaService.findAllVideos(),
				"Se esperaba VideoNotFoundException cuando no hay videos.");
	}

	@Test
	void when_FindVideosByTitle_should_return_videos_with_matching_title() throws VideoNotFoundException {
		List<Video> videos = culturemediaService.findVideosByTitle("Título 1");
		assertEquals(1, videos.size(), "Se esperaba que se devolviera el video con el título 'Título 1'.");
	}

	@Test
	void when_FindVideosByTitle_should_throw_VideoNotFoundException_when_no_videos_found() {
		assertThrows(VideoNotFoundException.class, () -> culturemediaService.findVideosByTitle("Título Inexistente"),
				"Se esperaba VideoNotFoundException cuando no hay videos con el título especificado.");
	}

	@Test
	void when_FindVideosByDuration_should_return_videos_in_duration_range() throws VideoNotFoundException {
		List<Video> videos = culturemediaService.findVideosByDuration(4.0, 5.0);
		assertEquals(2, videos.size(), "Se esperaba que se devolvieran 2 videos en el rango de duración.");
	}

	@Test
	void when_FindVideosByDuration_should_throw_VideoNotFoundException_when_no_videos_found() {
		assertThrows(VideoNotFoundException.class, () -> culturemediaService.findVideosByDuration(10.0, 15.0),
				"Se esperaba VideoNotFoundException cuando no hay videos en el rango de duración especificado.");
	}
}
