package com.example.panthera;

public class QuestionModel {

    private String questionCategory,questionTitle,question;
    private String userImage;

    public QuestionModel() {

    }

    public QuestionModel(String questionCategory, String questionTitle, String question, String userImage) {
        this.questionCategory = questionCategory;
        this.questionTitle = questionTitle;
        this.question = question;
        this.userImage = userImage;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }


}
