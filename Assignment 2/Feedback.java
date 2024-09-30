public class Feedback<V> {
    private V feedback;
    private String studentEmail;

    public Feedback(V feedback, String studentEmail) {
        this.feedback = feedback;
        this.studentEmail = studentEmail;
    }

    public V getFeedback() {
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