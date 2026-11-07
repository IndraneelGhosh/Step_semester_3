package week_eight.practice_systems;

import java.util.*;

/**
 * Question 3: Online Examination System
 */
abstract class Question {
    private final String questionText;
    private final int points;

    public Question(String questionText, int points) {
        this.questionText = questionText;
        this.points = points;
    }

    public String getQuestionText() { return questionText; }
    public int getPoints() { return points; }

    public abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends Question {
    private final String correctOption;

    public MultipleChoiceQuestion(String questionText, int points, String correctOption) {
        super(questionText, points);
        this.correctOption = correctOption;
    }

    @Override
    public boolean isCorrect(String answer) {
        return correctOption.equalsIgnoreCase(answer.trim());
    }
}

class TrueFalseQuestion extends Question {
    private final boolean correctAnswer;

    public TrueFalseQuestion(String questionText, int points, boolean correctAnswer) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return Boolean.toString(correctAnswer).equalsIgnoreCase(answer.trim());
    }
}

class Examination {
    private final String name;
    private final List<Question> questions = new ArrayList<>();

    public Examination(String name) {
        this.name = name;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public String getName() { return name; }
    public List<Question> getQuestions() { return questions; }
}

class ExamAttempt {
    enum State { IN_PROGRESS, SUBMITTED }

    private final String studentName;
    private final Examination exam;
    private final Map<Integer, String> recordedAnswers = new HashMap<>();
    private State state;

    public ExamAttempt(String studentName, Examination exam) {
        this.studentName = studentName;
        this.exam = exam;
        this.state = State.IN_PROGRESS;
        System.out.println(exam.getName() + " started by " + studentName + ".");
    }

    public void recordAnswer(int questionNumber, String answer) {
        if (state == State.SUBMITTED) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        recordedAnswers.put(questionNumber, answer);
        System.out.println("Answer recorded for Question " + questionNumber + ".");
    }

    public void submit() {
        if (state == State.SUBMITTED) {
            System.out.println("Exam has already been submitted.");
            return;
        }
        this.state = State.SUBMITTED;

        int totalScore = 0;
        int maxScore = 0;
        List<String> results = new ArrayList<>();

        List<Question> questions = exam.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            int qNum = i + 1;
            maxScore += q.getPoints();

            String ans = recordedAnswers.get(qNum);
            if (ans != null && q.isCorrect(ans)) {
                totalScore += q.getPoints();
                results.add("Question " + qNum + ": Correct (" + q.getPoints() + " points)");
            } else {
                results.add("Question " + qNum + ": Incorrect (0 points)");
            }
        }

        System.out.println(exam.getName() + " submitted by " + studentName + ". Result: " +
                           String.join(", ", results) + ". Total score: " + totalScore + "/" + maxScore + ".");
    }
}

public class OnlineExamSystem {
    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        examA.addQuestion(new MultipleChoiceQuestion("What is the time complexity of binary search?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion("Java arrays are dynamically sized.", 5, false));

        ExamAttempt attempt = new ExamAttempt("Student 1", examA);
        attempt.recordAnswer(1, "C");
        attempt.recordAnswer(2, "True");
        attempt.submit();

        // Attempting to change answer after submission
        attempt.recordAnswer(1, "B");
    }
}