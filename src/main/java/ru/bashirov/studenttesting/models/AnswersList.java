package ru.bashirov.studenttesting.models;

import java.util.ArrayList;

public final class AnswersList {

    private ArrayList<Question> answers;

    private int allQuestionsCount;

    public AnswersList(ArrayList<Question> answers) {
        this.answers = answers;
    }

    public ArrayList<Question> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Question> answers) {
        this.answers = answers;
    }

    public int getAllQuestionsCount() {
        return allQuestionsCount;
    }

    public void setAllQuestionsCount(int allQuestionsCount) {
        this.allQuestionsCount = allQuestionsCount;
    }
}
