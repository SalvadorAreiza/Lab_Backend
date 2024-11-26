package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.service.CultureMediaService;

import java.util.List;
import java.util.Optional;

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


    public Video findById(Long id) throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(id);
        return video.orElseThrow(() -> new VideoNotFoundException("El video con ID " + id + " no fue encontrado."));
    }

    public List<Video> save(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public void deleteById(Long id) {

    }

}
