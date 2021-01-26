package com.example.pokemon.ui.main;

import java.io.Serializable;

public class PokeStats implements Serializable {

    private int pokemon_id;
    private String pokemon_name;
    private int base_stamina;
    private int base_defense;
    private int base_attack;

    public int getId() {
        return pokemon_id;
    }

    public void setId(int id) {
        this.pokemon_id = id;
    }

    public String getName() {
        return pokemon_name;
    }

    public void setName(String name) {
        this.pokemon_name = name;
    }

    public int getStamina() {
        return base_stamina;
    }

    public void setStamina(int stamina) {
        this.base_stamina = stamina;
    }

    public int getDefense() {
        return base_defense;
    }

    public void setDefense(int defense) {
        this.base_defense = defense;
    }

    public int getAttack() {
        return base_attack;
    }

    public void setAttack(int attack) {
        this.base_attack = attack;
    }

    @Override
    public String toString() {
        return "PokeStats{" +
                "id=" + pokemon_id +
                ", name='" + pokemon_name + '\'' +
                ", stamina=" + base_stamina +
                ", defense=" + base_defense +
                ", attack=" + base_attack +
                '}';
    }
}
