package culturemedia.repository.Impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.ViewsRepository;


public class ViewsRepositoryImpl1 implements ViewsRepository {


    @Override
    public void view(View view) {

    }

    @Override
    public View save(View view) {

        return view;
    }

    @Override
    public Video save(Video video) {
        return null;
    }
}
