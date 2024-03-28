import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class representing a generic question
abstract class Question {
    private String prompt;
    private String answer;

    public Question(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getAnswer() {
        return answer;
    }

    // Abstract method to check the answer
    public abstract boolean checkAnswer(String userAnswer);
}

// MultipleChoiceQuestion class representing a multiple choice question
class MultipleChoiceQuestion extends Question {
    private List<String> options;

    public MultipleChoiceQuestion(String prompt, List<String> options, String answer) {
        super(prompt, answer);
        this.options = options;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        // Convert userAnswer to index (e.g., A -> 0, B -> 1, etc.)
        int userIndex = userAnswer.toUpperCase().charAt(0) - 'A';
        return getAnswer().equalsIgnoreCase(options.get(userIndex));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPrompt()).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append((char) ('A' + i)).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}

// TrueFalseQuestion class representing a true/false question
class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(String prompt, String answer) {
        super(prompt, answer);
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return getAnswer().equalsIgnoreCase(userAnswer);
    }

    @Override
    public String toString() {
        return getPrompt() + " (True/False)\n";
    }
}

// Quiz class representing the quiz
class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        for (Question question : questions) {
            System.out.println(question);
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().trim();
            if (question.checkAnswer(userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect!\n");
            }
        }
        System.out.println("Quiz completed. Your score: " + score + "/" + questions.size());
    }
}

public class Main {
    public static void main(String[] args) {
        // Create quiz
        Quiz quiz = new Quiz();

        // Add questions
        quiz.addQuestion(new MultipleChoiceQuestion(
                "What is the capital of France?",
                List.of("Paris", "London", "Rome", "Berlin"),
                "Paris"
        ));

        quiz.addQuestion(new TrueFalseQuestion(
                "The Earth is flat. (True or False)",
                "False"
        ));

        // Start quiz
        quiz.start();
    }
}
