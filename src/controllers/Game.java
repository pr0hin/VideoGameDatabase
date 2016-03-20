package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

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

    private IntegerProperty year;
    public void setYear(Integer value) { yearProperty().set(value); }
    public Integer getYear() { return yearProperty().get(); }
    public IntegerProperty yearProperty() {
        if (year == null) year = new SimpleIntegerProperty(this, "year");
        return year;
    }
}
