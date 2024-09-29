public class Complaint {
    private String complaint;
    private String email;
    private String status;

    public Complaint(String complaint, String email) {
        this.complaint = complaint;
        this.email = email;
        this.status = "Pending";
    }

    public String getComplaint() {
        return complaint;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
