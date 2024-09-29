public class Feedback<T> {
    private T feedback;
    private String studentEmail;

    public Feedback(T feedback, String studentEmail) {
        this.feedback = feedback;
        this.studentEmail = studentEmail;
    }

    public T getFeedback() {
        return feedback;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    @Override
    public String toString() {
        return String.format("| %-30s | %-50s |", studentEmail, feedback.toString());
    }
}