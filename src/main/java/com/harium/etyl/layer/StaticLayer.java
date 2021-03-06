package com.harium.etyl.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.loadstack.LayerLoadStack;
import com.harium.etyl.loader.async.AsyncResource;
import com.harium.etyl.loader.image.ImageLoader;
import com.harium.etyl.util.io.IOHelper;

/**
 * @author yuripourre
 */

public class StaticLayer extends Layer implements AsyncResource<Texture> {

    protected boolean loaded = false;
    protected Texture texture;
    protected String path = "";

    public StaticLayer() {
        super();
    }

    /**
     * @param x
     * @param y
     */
    public StaticLayer(int x, int y) {
        super(x, y);
    }

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public StaticLayer(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     * @param path
     */
    public StaticLayer(int x, int y, int w, int h, String path) {
        super(x, y);

        setPath(path);
    }

    /**
     * @param path
     */
    public StaticLayer(String path) {
        setPath(path);
    }

    public StaticLayer(String path, boolean absolute) {
        setPath(path, absolute);
    }

    private void setPath(String path, boolean absolute) {
        this.path = path;
        load(absolute);
    }

    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        setPath(path, false);
    }

    /**
     * @param w
     * @param h
     */
    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    /**
     * @param path
     */
    public void cloneLayer(String path) {
        this.path = path;
    }

    /**
     * @param layer
     */
    public void cloneLayer(StaticLayer layer) {
        path = layer.path;
        texture = layer.texture;
        loaded = layer.loaded;

        w = layer.getW();
        h = layer.getH();
        originX = layer.getOriginX();
        originY = layer.getOriginY();
    }

    public void load(final boolean absolute) {
        final AsyncResource resource = this;

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                String check = path;
                if (path.startsWith(IOHelper.STREAM_PREFIX)) {
                    check = path.substring(IOHelper.STREAM_PREFIX.length());
                }

                if (ImageLoader.getInstance().isLoaded(check, absolute)) {
                    Texture texture = ImageLoader.getInstance().get(check, absolute);
                    onLoad(texture);
                    return;
                }
                ImageLoader.getInstance().loadTextureAsync(path, absolute, resource);
            }});
    }

    @Override
    public void onLoad(Texture texture) {
        this.texture = texture;
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.w = texture.getWidth();
        this.h = texture.getHeight();
        setOriginCenter();
        this.loaded = true;

        notifyListeners();
    }

    @Override
    public final Class<Texture> resourceClass() {
        return Texture.class;
    }

    protected void notifyListeners() {
        LayerLoadStack.onLoad(this);
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }
}