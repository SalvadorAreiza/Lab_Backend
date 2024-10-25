package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.service.CultureMediaService;

import java.util.List;

public class CultureMediaServiceImpl implements CultureMediaService {

    private final VideoRepository videoRepository;

    public CultureMediaServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No se encontraron videos.");
        }
        return videos;
    }

    @Override
    public List<Video> find(String title) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(title);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No se encontraron videos con el título: " + title);
        }
        return videos;
    }

    @Override
    public List<Video> find(double fromDuration, double toDuration) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(fromDuration, toDuration);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No se encontraron videos en el rango de duración: " + fromDuration + " - " + toDuration);
        }
        return videos;
    }



}
