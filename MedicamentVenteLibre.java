public class MedicamentVenteLibre extends Medicament {
    private String indications;

    public MedicamentVenteLibre(int id, String nom, double prix, String fabricant, int stock, String indications) {
        super(id, nom, prix, fabricant, stock);
        this.indications = indications;
    }

    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Indications: " + indications);
    }
}
