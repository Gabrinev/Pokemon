package com.example.pokemon.ui.main;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Pokemon implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ide;
    private String name;
    private boolean found_egg;
    private boolean found_raid;
    private boolean found_research;
    private boolean found_wild;

    public int getIde() {
        return ide;
    }

    public void setIde(int id) {
        this.ide = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFound_egg() {
        return found_egg;
    }

    public void setFound_egg(boolean found_egg) {
        this.found_egg = found_egg;
    }

    public boolean isFound_raid() {
        return found_raid;
    }

    public void setFound_raid(boolean found_raid) {
        this.found_raid = found_raid;
    }

    public boolean isFound_research() {
        return found_research;
    }

    public void setFound_research(boolean found_research) {
        this.found_research = found_research;
    }

    public boolean isFound_wild() {
        return found_wild;
    }

    public void setFound_wild(boolean found_wild) {
        this.found_wild = found_wild;
    }


    public String toString() {
        return "Movie{" +
                "id='" + ide + '\'' +
                ", name='" + name + '\'' +
                ", egg='" + found_egg + '\'' +
                ", raid='" + found_raid + '\'' +
                ", research='" + found_research + '\'' +
                '}';
    }
}
