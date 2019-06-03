package edwy.lugo.pokemonfinder.model;

import java.io.Serializable;

public class Types implements Serializable {

    private String thumbnailImage;
    private String name;

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
