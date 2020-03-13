package com.techneapps.triviaapp.repo;

import com.techneapps.triviaapp.database.models.QuestionAnswer;

public class StaticQuestionsRepo {
    public static QuestionAnswer getFirstQuestion() {
        return new QuestionAnswer(
                "Who is the best cricketer in the world?",
                "Sachin Tendulkar",
                "Virat Kohli",
                "Adam Gilchirst",
                "Jacques Kallis");
    }

    public static QuestionAnswer getSecondQuestion() {
        return new QuestionAnswer(
                "What are the colors in the Indian national flag? Select all:",
                "White",
                "Yellow",
                "Orange",
                "Green");
    }
}
