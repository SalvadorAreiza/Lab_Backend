package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;

import java.util.List;

public interface CultureMediaService {


    List<Video> findAll() throws VideoNotFoundException;
    List<Video> find(String title) throws VideoNotFoundException;
    List<Video> find(double minDuration, double maxDuration) throws VideoNotFoundException;
}

