package culturemedia.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.ViewsRepository;

public class ViewsRepositoryImpl implements ViewsRepository {

	private final List<Video> videos;
	private final List<View> views;

	public ViewsRepositoryImpl() {
		this.videos = new ArrayList<>();
		this.views = new ArrayList<>();
	}

	@Override
	public void view(View view) {
		this.views.add(view);
		//return view;
	}

	@Override
	public View save(View view) {
		this.views.add(view);
		return view;  // Cambiado para devolver el objeto guardado
	}

	@Override
	public Video save(Video video) {
		this.videos.add(video);
		return video;  // Cambiado para devolver el objeto guardado
	}

	@Override
	public List<Video> findAll() throws VideoNotFoundException {
		if (videos.isEmpty()) {
			throw new VideoNotFoundException("No se encontraron videos en el repositorio.");
		}
		return videos;
	}

	@Override
	public List<Video> find(String title) {
		List<Video> findVideos = new ArrayList<>();
		for (Video video : videos) {
			if (title.equals(video.title())) {
				findVideos.add(video);
			}
		}
		return findVideos;
	}

	@Override
	public List<Video> find(Double fromDuration, Double toDuration) {
		List<Video> findVideos = new ArrayList<>();
		for (Video video : videos) {
			if (video.duration() >= fromDuration && video.duration() <= toDuration) {
				findVideos.add(video);
			}
		}
		return findVideos;
	}



}