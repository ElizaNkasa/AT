// Représente un médicament nécessitant une ordonnance
public class MedicamentAOrdonnance extends Medicament {
    private String dosage;             // Ex : "500mg"
    private String contreIndication;  // Ex : "Allergie à la pénicilline"

    // Constructeur
    public MedicamentAOrdonnance(int id, String nom, double prix, String fabricant, int stock, String dosage, String contreIndication) {
        super(id, nom, prix, fabricant, stock);
        this.dosage = dosage;
        this.contreIndication = contreIndication;
    }

    // Affichage des informations avec dosage et contre-indications
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Dosage : " + dosage);
        System.out.println("Contre-indication : " + contreIndication);
    }

    // Getters (facultatifs mais utiles)
    public String getDosage() {
        return dosage;
    }

    public String getContreIndication() {
        return contreIndication;
    }
}
