import java.util.Date;

public class Ordonnance {
    private int idOrdonnance;
    private Date dateOrdonnance;
    private String medecin;

    public Ordonnance(int idOrdonnance, Date dateOrdonnance, String medecin) {
        this.idOrdonnance = idOrdonnance;
        this.dateOrdonnance = dateOrdonnance;
        this.medecin = medecin;
    }

    public void verifierOrdonnance() {
        System.out.println("Ordonnance ID: " + idOrdonnance + " - Émise le: " + dateOrdonnance + " par Dr. " + medecin);
    }

    // Getters
    public int getIdOrdonnance() {
        return idOrdonnance;
    }

    public Date getDateOrdonnance() {
        return dateOrdonnance;
    }

    public String getMedecin() {
        return medecin;
    }

    // Setters
    public void setIdOrdonnance(int idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
    }

    public void setDateOrdonnance(Date dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }
}
