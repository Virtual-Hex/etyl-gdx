package com.harium.etyl.loader;

public class Loader {
    protected Assets assets;

    private String folder;
    private String path = "";

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public String getPath() {
        return path;
    }

    public void setUrl(String url) {
        this.path = url;
    }

    public String fullPath() {
        return path + folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
