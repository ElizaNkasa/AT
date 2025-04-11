import java.util.Date;

public class Vente {
    private static int compteur = 1;
    private int idVente;
    private Date dateVente;
    private double montantTotal;

    public Vente(Date dateVente, double montantTotal) {
        this.idVente = compteur++;
        this.dateVente = dateVente;
        this.montantTotal = montantTotal;
    }

    public void enregistrerVente() {
        System.out.println("Vente ID: " + idVente + " | Date: " + dateVente + " | Montant: " + montantTotal + "€.");
    }

    // Getters
    public int getIdVente() {
        return idVente;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    // Setters
    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
}
