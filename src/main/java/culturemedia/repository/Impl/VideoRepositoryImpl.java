package culturemedia.repository.Impl;

import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

import java.util.List;

public class VideoRepositoryImpl implements VideoRepository {


    @Override
    public List<Video> listAll() {
        return List.of();
    }

    @Override
    public void save(Video video) {

    }

    @Override
    public List<Video> find(String title) {
        return List.of();
    }

    @Override
    public List<Video> find(Double fromDuration, Double toDuration) {
        return List.of();
    }
}
