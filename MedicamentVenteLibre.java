// Représente un médicament en vente libre (sans ordonnance)
public class MedicamentVenteLibre extends Medicament {
    private String indications; // Usage ou pathologies ciblées

    // Constructeur
    public MedicamentVenteLibre(int id, String nom, double prix, String fabricant, int stock, String indications) {
        super(id, nom, prix, fabricant, stock);
        this.indications = indications;
    }

    // Affichage des informations avec les indications spécifiques
    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Indications : " + indications);
    }

    // Getter pour indications (facultatif mais utile selon le reste du code)
    public String getIndications() {
        return indications;
    }
}
