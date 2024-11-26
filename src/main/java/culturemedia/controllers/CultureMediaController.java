package culturemedia.controllers;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CultureMediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Video>> findAllVideos() throws VideoNotFoundException {
		List<Video> videos = cultureMediaService.findAll();
		if (videos == null || videos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(null); // Retorna 404 si no hay videos.
		}
		return ResponseEntity.ok(videos);
	}

	/**
	 * Obtiene un video por su ID.
	 *
	 * @param id ID del video.
	 * @return Video correspondiente al ID.
	 * @throws VideoNotFoundException Si no se encuentra el video.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Video> findVideoById(@PathVariable Long id) {
		try {
			Video video = cultureMediaService.findById(id);
			return ResponseEntity.ok(video);
		} catch (VideoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Agrega un nuevo video.
	 *
	 * @param video Objeto Video recibido en el cuerpo de la solicitud.
	 * @return Video creado.
	 */
	@PostMapping
	public ResponseEntity<List<Video>> addVideo(@RequestBody Video video) {
		List<Video> createdVideo = cultureMediaService.save(video);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdVideo);
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
	public ResponseEntity<List<Video>> updateVideo(@PathVariable Long id, @RequestBody Video video) {
		try {
			if (!cultureMediaService.existsById(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			video.setId(id); // Asegurar que el ID coincida.
			List<Video> updatedVideo = cultureMediaService.save(video);
			return ResponseEntity.ok(updatedVideo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Elimina un video por su ID.
	 *
	 * @param id ID del video a eliminar.
	 * @return Respuesta de eliminación.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
		if (!cultureMediaService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		cultureMediaService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}