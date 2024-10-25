package culturemedia.repository.Impl;
import java.util.ArrayList;
import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VideoRepositoryImpl implements VideoRepository {

	private final List<Video> videos;
	private final List<View> views;

	public VideoRepositoryImpl() {
		videos = new ArrayList<>();
		views = new ArrayList<>();
	}


	@Override
	public List<Video> findAll() throws VideoNotFoundException {
		if (videos.isEmpty()) {
			throw new VideoNotFoundException("No se encontraron videos en el repositorio.");
		}
		return videos;
	}


	@Override
	public List<Video> save(Video video) {
		this.videos.add( video );
		return videos;
	}

	@Override
	public List<View> save(View view) {
		this.views.add( view );
		return views;
	}

	@Override
	public List<Video> find(String title) {
		List<Video> filteredVideos = null;
		for ( Video video : videos ) {
			if(title.equals( video.title() )){
				if(filteredVideos == null){
					filteredVideos = new ArrayList<Video>();
				}
				filteredVideos.add(video);
			}
		}
		return filteredVideos;
	}

	@Override
	public List<Video> find(Double fromDuration, Double toDuration) {
		List<Video> filteredVideos = new ArrayList<Video>();
		for ( Video video : videos ) {
			if(video.duration()> fromDuration && video.duration()< toDuration){
				filteredVideos.add(video);
			}
		}
		return filteredVideos;
	}






}
