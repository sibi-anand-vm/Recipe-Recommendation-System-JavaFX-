package com.example.project2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Recipe {

    private final StringProperty name;
    private final StringProperty region;
    private final StringProperty type;
    private final StringProperty taste;

    public Recipe(String name, String region, String type, String taste) {
        this.name = new SimpleStringProperty(name);
        this.region = new SimpleStringProperty(region);
        this.type = new SimpleStringProperty(type);
        this.taste = new SimpleStringProperty(taste);
    }

    public String getName() {
        return name.get();
    }

    public String getRegion() {
        return region.get();
    }

    public String getType() {
        return type.get();
    }

    public String getTaste() {
        return taste.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty regionProperty() {
        return region;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty tasteProperty() {
        return taste;
    }
}
