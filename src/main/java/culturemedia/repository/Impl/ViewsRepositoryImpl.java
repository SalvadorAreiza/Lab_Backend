package culturoteca.repository.impl;

import java.util.ArrayList;
import java.util.List;

import culturoteca.model.View;
import culturoteca.repository.ViewsRepository;

public class ViewsRepositoryImpl implements ViewsRepository {

	private final List<View> views;

	public ViewsRepositoryImpl() {
		this.views = new ArrayList<>();
	}

	@Override
	public View save(View view) {
		this.views.add( view );
		return view;
	}
}
