package com.example.panthera;

public class GuideModel {

    private String guide_name, guide_description, guide_address, guide_contact, guide_languages;
    private String image1;

    public GuideModel() {

    }

    public GuideModel(String guide_name, String guide_description, String guide_address, String guide_contact, String guide_languages, String image1) {
        this.guide_name = guide_name;
        this.guide_description = guide_description;
        this.guide_address = guide_address;
        this.guide_contact = guide_contact;
        this.guide_languages = guide_languages;
        this.image1 = image1;
    }

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public String getGuide_description() {
        return guide_description;
    }

    public void setGuide_description(String guide_description) {
        this.guide_description = guide_description;
    }

    public String getGuide_address() {
        return guide_address;
    }

    public void setGuide_address(String guide_address) {
        this.guide_address = guide_address;
    }

    public String getGuide_contact() {
        return guide_contact;
    }

    public void setGuide_contact(String guide_contact) {
        this.guide_contact = guide_contact;
    }

    public String getGuide_languages() {
        return guide_languages;
    }

    public void setGuide_languages(String guide_languages) {
        this.guide_languages = guide_languages;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }
}
