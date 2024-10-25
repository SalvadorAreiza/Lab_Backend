package culturemedia.repository;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;

import java.util.List;

public interface ViewsRepository {
    //  Metodo agregar Reproduccion
    void view(View view);

    View save(View view);

    List<Video> findAll() throws VideoNotFoundException;

    Video save(Video video);

    List<Video> find(String title);

    List<Video> find(Double fromDuration, Double toDuration);
}


