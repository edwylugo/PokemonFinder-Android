package edwy.lugo.pokemonfinder.model;

import java.io.Serializable;
import java.util.List;

public class Pokemons implements Serializable {

    private List<String> type;
    private String name;
    private String thumbnailImage;

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }
}
