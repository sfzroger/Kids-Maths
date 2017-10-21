package www.earlylearning.me.kidsmath;

import java.io.Serializable;

/**
 * Created by user on 21/10/2017.
 */

public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    private int qId;
    private String question;
    private String correctAnswer;
    private String userAnswer;

    public QuestionAnswer()
    {}

    public QuestionAnswer(int qId, String question, String correctAnswer, String userAnswer)
    {
        super();
        this.qId = qId;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }

    public void SetQId(int qid)
    {
        this.qId = qid;
    }

    public int GetQId()
    {
        return this.qId;
    }

    public void SetQuestion(String question)
    {
        this.question = question;
    }

    public String GetQuestion()
    {
        return this.question;
    }

    public void SetCorrectAnswer(String answer)
    {
        this.correctAnswer = answer;
    }

    public String GetCorrectAnswer()
    {
        return this.correctAnswer;
    }

    public void SetUserAnswer(String answer)
    {
        this.userAnswer = answer;
    }

    public String GetUserAnswer()
    {
        return this.userAnswer;
    }
}
