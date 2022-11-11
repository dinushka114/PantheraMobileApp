package com.example.panthera;

public class model
{
  String questionCategory,questionTitle,question,purl;
    model()
    {

    }
    public model(String questionCategory, String questionTitle, String question, String purl) {
        this.questionCategory = questionCategory;
        this.questionTitle = questionTitle;
        this.question = question;
        this.purl = purl;
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

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
