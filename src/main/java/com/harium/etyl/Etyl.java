package com.harium.etyl;


import com.harium.etyl.commons.context.Application;
import com.harium.etyl.core.GDXCore;
import com.harium.etyl.core.Engine;
import com.harium.etyl.loader.FontLoader;
import com.harium.etyl.loader.MultimediaLoader;
import com.harium.etyl.loader.image.ImageLoader;

public abstract class Etyl extends BaseEngine<GDXCore> implements Engine<Application> {

    public static final String WINDOW = "window";
    private Application application;

    public Etyl(int w, int h) {
        super(w, h);
        addLoader(ImageLoader.getInstance());
        addLoader(FontLoader.getInstance());
        addLoader(MultimediaLoader.getInstance());
    }

    public void init() {
        initialSetup("");

        application = startApplication();
        application.setLoaded(false);
        core.setApplication(application);

        super.init();
    }

    protected GDXCore initCore() {
        return new GDXCore(w, h);
    }

}
