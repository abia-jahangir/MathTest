import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MathTest {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in); 
        int int_num_questions = 0;

        do {
            System.out.println("How many questions do you want?");
            if(scanner.hasNextInt()) {
                int_num_questions = scanner.nextInt();
                System.out.print("\n");     
                break;       
            }
            else {
                System.out.println("Input a valid value.");
                scanner.nextLine();
                System.out.print("\n");
            }
        } while (true);

        System.out.println("Input all your answers to 2 decimal places.\n\nGoodluck!\n");

        double correct_answers = 0;

        for (int i = 1; i <= int_num_questions; i++){
            QuestionAnswer qa = createQuestion();
            String question = qa.getQuestion();
            double answer = qa.getAnswer();

            double user_ans = 0;

            do {
                System.out.println("Q" + i + ": " + question);
                if(scanner.hasNextDouble()) {
                    user_ans = scanner.nextDouble();
                    System.out.print("\n");     
                    break;       
                }
                else {
                    System.out.println("Input a valid value.");
                    scanner.nextLine();
                    System.out.print("\n");
                }
            } while (true);

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedAnswer = decimalFormat.format(answer);
            String formattedUserAnswer = decimalFormat.format(user_ans);

            if (formattedAnswer.equals(formattedUserAnswer)){
                correct_answers++;
                System.out.println("Correct!\n");
            }
            else{
                System.out.printf(formattedUserAnswer + " is incorrect. The correct answer was: "+ formattedAnswer + "\n");
            }
        }
        double grade = correct_answers/int_num_questions * 100;

        if (grade < 50){
            System.out.printf("Better luck next time... You got %.2f%%.", grade);
        }
        else if ((grade >= 50) && (grade <= 75)){
            System.out.printf("Keep working hard! You got %.2f%%.", grade);
        }
        else if (grade > 75 && grade <100){
            System.out.printf("Great! You got %.2f%%.", grade);
        }
        else if (grade == 100){
            System.out.printf("Perfect! You got %.2f%%.", grade);
        }
    }

    public static QuestionAnswer createQuestion(){
            
        // Define a list of operators
        List<String> operators = Arrays.asList("+", "-", "*", "/");

        // Create a Random instance
        Random random = new Random();

        // Generate a random index
        int randomIndex = random.nextInt(operators.size());

        // Get the random operator
        String randomOperator = operators.get(randomIndex);

        Random rand = new Random();

        int val1 = rand.nextInt(500);
        int val2 = rand.nextInt(500);
        String question = "";
        double answer = 0.00;

        if (randomOperator == "+"){
            question = val1 + " + " + val2 + " = ?";
            answer = val1 + val2;
        }
        else if (randomOperator == "-"){
            question = val1 + " - " + val2 + " = ?";
            answer = val1 - val2;
        }
        else if (randomOperator == "*"){
            question = val1 + " * " + val2 + " = ?";
            answer = val1 * val2;
        }
        else if (randomOperator == "/"){
            question = val1 + " / " + val2 + " = ?";
            answer = (double)val1 / val2;
            String formattedAnswer = String.format("%.2f", answer);
            answer = Double.valueOf(formattedAnswer);
        }

        return new QuestionAnswer(question, answer);
    }

    static class QuestionAnswer {
        private String question;
        private double answer;
    
        public QuestionAnswer(String question, double answer) {
            this.question = question;
            this.answer = answer;
        }
    
        public String getQuestion() {
            return question;
        }
    
        public double getAnswer() {
            return answer;
        }
    }

}
