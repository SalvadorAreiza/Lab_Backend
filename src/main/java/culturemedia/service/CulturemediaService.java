package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

import java.util.List;

public class CulturemediaService {
    private final VideoRepository videoRepository;

    public CulturemediaService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No se encontraron videos.");
        }
        return videos;
    }

    public List<Video> findVideosByTitle(String title) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(title);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No se encontraron videos con el título: " + title);
        }
        return videos;
    }

    public List<Video> findVideosByDuration(double fromDuration, double toDuration) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(fromDuration, toDuration);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No se encontraron videos en el rango de duración: " + fromDuration + " - " + toDuration);
        }
        return videos;
    }
}
