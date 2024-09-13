public class Complaint {
    private String complaint;
    private String emailID;
    private String status;

    public Complaint(String complaint, String emailID) {
        this.complaint = complaint;
        this.emailID = emailID;
        this.status = "Pending";
    }

    public String getComplaint() {
        return complaint;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
