package com.example.panthera.models;

//Model class for endangered animals
public class Animal {

    private int id;
    private String name;
    private String scientificName;
    private String description;
    private byte[] animalImage;

    public Animal() {
    }

    public Animal(int id, String name, String scientificName, String description, byte[] animalImage) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.description = description;
        this.animalImage = animalImage;
    }

    public Animal(String name, String scientificName, String description, byte[] animalImage) {
        this.name = name;
        this.scientificName = scientificName;
        this.description = description;
        this.animalImage = animalImage;
    }

    public Animal(int id, String name, String scientificName, String description) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAnimalImage() {
        return animalImage;
    }

    public void setAnimalImage(byte[] animalImage) {
        this.animalImage = animalImage;
    }
}
