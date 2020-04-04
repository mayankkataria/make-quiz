package com.jumayu.nptelmcq;

import java.io.Serializable;

public class Question implements Serializable {

    String question, opt1, opt2, opt3, opt4;
    Integer correctAnsId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public Integer getCorrOpt() {
        return correctAnsId;
    }

    public void setCorrOpt(Integer correctAns) {
        this.correctAnsId = correctAns;
    }
}
