public class MedicamentAOrdonnance extends Medicament {
    private String dosage;
    private String contreIndication;

    public MedicamentAOrdonnance(int id, String nom, double prix, String fabricant, int stock, String dosage, String contreIndication) {
        super(id, nom, prix, fabricant, stock);
        this.dosage = dosage;
        this.contreIndication = contreIndication;
    }

    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Dosage: " + dosage + ", Contre-indication: " + contreIndication);
    }
}
