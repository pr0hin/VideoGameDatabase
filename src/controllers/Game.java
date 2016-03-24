package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by kevinchiang on 2016-03-19.
 */
public class Game {
    private StringProperty title;
    public void setTitle(String value) { titleProperty().set(value); }
    public String getTitle() { return titleProperty().get(); }
    public StringProperty titleProperty() {
        if (title == null) title = new SimpleStringProperty(this, "title");
        return title;
    }

    private StringProperty year;
    public void setYear(String value) { yearProperty().set(value); }
    public String getYear() { return yearProperty().get(); }
    public StringProperty yearProperty() {
        if (year == null) year = new SimpleStringProperty(this, "year");
        return year;
    }

    private StringProperty platform;
    public void setPlatform(String value) { platformProperty().set(value); }
    public String getPlatform() { return platformProperty().get(); }
    public StringProperty platformProperty() {
        if (platform == null) platform = new SimpleStringProperty(this, "platform");
        return platform;
    }

    private StringProperty genre;
    public void setGenre(String value) { genreProperty().set(value); }
    public String getGenre() { return genreProperty().get(); }
    public StringProperty genreProperty() {
        if (genre == null) genre = new SimpleStringProperty(this, "genre");
        return genre;
    }

    private StringProperty devname;
    public void setDevname(String value) { devnameProperty().set(value); }
    public String getDevname() { return devnameProperty().get(); }
    public StringProperty devnameProperty() {
        if (devname == null) devname = new SimpleStringProperty(this, "devname");
        return devname;
    }

    private StringProperty pubname;
    public void setPubname(String value) { pubnameProperty().set(value); }
    public String getPubname() { return pubnameProperty().get(); }
    public StringProperty pubnameProperty() {
        if (pubname == null) pubname = new SimpleStringProperty(this, "pubname");
        return pubname;
    }
}
