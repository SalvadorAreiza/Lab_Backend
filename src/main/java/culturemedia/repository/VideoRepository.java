package culturemedia.repository;

import java.util.List;

import culturemedia.model.Video;

public interface VideoRepository {
    // Metodo listar todos los videos
    List<Video> listAll();

    // Metodo agregar video
    void save(Video video);

    // Metodo listar video por titulo
    List<Video> find(String title);

    // Metodo listar video por duracion
    List<Video> find(Double fromDuration, Double toDuration);
}
