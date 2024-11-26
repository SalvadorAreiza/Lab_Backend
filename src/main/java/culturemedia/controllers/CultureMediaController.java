package culturemedia.controllers;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CultureMediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para manejar las operaciones de CultureMedia.
 */
@RestController
@RequestMapping("/api/videos")
public class CultureMediaController {

	private final CultureMediaService cultureMediaService;

	@Autowired
	public CultureMediaController(CultureMediaService cultureMediaService) {
		this.cultureMediaService = cultureMediaService;
	}

	/**
	 * Obtiene la lista de todos los videos.
	 *
	 * @return Lista de videos.
	 * @throws VideoNotFoundException Si no se encuentran videos.
	 */
	@GetMapping
	public List<Video> findAllVideos() throws VideoNotFoundException {
		List<Video> videos = cultureMediaService.findAll();
		if (videos == null || videos.isEmpty()) {
			throw new VideoNotFoundException("No se encontraron videos.");
		}
		return videos;
	}

	/**
	 * Obtiene un video por su ID.
	 *
	 * @param id ID del video.
	 * @return Video correspondiente al ID.
	 * @throws VideoNotFoundException Si no se encuentra el video.
	 */
	@GetMapping("/{id}")
	public Video findVideoById(@PathVariable Long id) throws VideoNotFoundException {
		return cultureMediaService.findById(id)
				.orElseThrow(() -> new VideoNotFoundException("El video con ID " + id + " no fue encontrado."));
	}

	/**
	 * Agrega un nuevo video.
	 *
	 * @param video Objeto Video recibido en el cuerpo de la solicitud.
	 * @return Video creado.
	 */
	@PostMapping
	public Video addVideo(@RequestBody Video video) {
		return cultureMediaService.save(video);
	}

	/**
	 * Actualiza un video existente.
	 *
	 * @param id    ID del video a actualizar.
	 * @param video Objeto Video con los datos actualizados.
	 * @return Video actualizado.
	 * @throws VideoNotFoundException Si no se encuentra el video.
	 */
	@PutMapping("/{id}")
	public Video updateVideo(@PathVariable Long id, @RequestBody Video video) throws VideoNotFoundException {
		if (!cultureMediaService.existsById(id)) {
			throw new VideoNotFoundException("El video con ID " + id + " no existe.");
		}
		video.setId(id); // Asegurar que el ID coincida.
		return cultureMediaService.save(video);
	}

	/**
	 * Elimina un video por su ID.
	 *
	 * @param id ID del video a eliminar.
	 * @throws VideoNotFoundException Si no se encuentra el video.
	 */
	@DeleteMapping("/{id}")
	public void deleteVideo(@PathVariable Long id) throws VideoNotFoundException {
		if (!cultureMediaService.existsById(id)) {
			throw new VideoNotFoundException("El video con ID " + id + " no existe.");
		}
		cultureMediaService.deleteById(id);
	}
}