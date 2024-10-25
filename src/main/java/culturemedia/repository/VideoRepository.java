package culturemedia.repository;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;

public interface VideoRepository {
    // Metodo listar todos los videos
    List<Video> findAll() throws VideoNotFoundException;

    // Metodo agregar video
    List<Video> save(Video video);

    List<View> save(View view);

    // Metodo listar video por titulo
    List<Video> find(String title);

    // Metodo listar video por duracion
    List<Video> find(Double fromDuration, Double toDuration);
}
