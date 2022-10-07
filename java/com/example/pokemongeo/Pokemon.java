package com.example.pokemongeo;

public class Pokemon {
    private int order;
    private String name;
    private int height;
    private int weight;
    private int frontResource;
    private POKEMON_TYPE type1;
    private POKEMON_TYPE type2;
    private int HP;
    private int Attack;
    private int Defense;
    private int Speed;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getDefense() {
        return Defense;
    }

    public void setDefense(int defense) {
        Defense = defense;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int speed) {
        Speed = speed;
    }

    public Pokemon() {
        order = 1;
        name = "Unknown";
        frontResource = R.drawable.p3;
        type1 = POKEMON_TYPE.Plant;
    }
    public Pokemon(int order, String name, int frontResource,
                   POKEMON_TYPE type1, POKEMON_TYPE type2) {
        this.order = order;
        this.name = name;
        this.frontResource = frontResource;
        this.type1 = type1;
        this.type2 = type2;
    }

    public Pokemon(int order, String name, int frontResource, POKEMON_TYPE type1, POKEMON_TYPE type2, int HP, int attack, int defense, int speed) {
        this.order = order;
        this.name = name;
        this.frontResource = frontResource;
        this.type1 = type1;
        this.type2 = type2;
        this.HP = HP;
        Attack = attack;
        Defense = defense;
        Speed = speed;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getFrontResource() {
        return frontResource;
    }
    public void setFrontResource(int frontResource) {
        this.frontResource = frontResource;
    }
    public POKEMON_TYPE getType1() {
        return type1;
    }
    public void setType1(POKEMON_TYPE type1) {this.type1 = type1;
    }
    public POKEMON_TYPE getType2() {
        return type2;
    }
    public void setType2(POKEMON_TYPE type2) {
        this.type2 = type2;
    }
    public String getType1String() {
        return type1.name();
    }
    public String getType2String() {
        return type2.name();
    }
}
enum POKEMON_TYPE {
    Steel,
    Fighting,
    Dragon,
    Water,
    Electric,
    Fairy,
    Fire,
    Ice,
    Bug,
    Normal,
    Plant,
    Poison,
    Psychic,
    Rock,
    Ground,
    Spectrum,
    Darkness,
    Flying,
    Grass,
    Ghost
}