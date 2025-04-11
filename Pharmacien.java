import java.util.Date;

public class Pharmacien {
    private String nom;
    private int id;
    private String adresse;
    private String telephone;

    public Pharmacien(String nom, int id, String adresse, String telephone) {
        this.nom = nom;
        this.id = id;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public void gererStock(Medicament medicament, int quantite) {
        medicament.setStock(medicament.getStock() + quantite);
        System.out.println("Stock mis à jour : +" + quantite + " unités pour " + medicament.getNom());
    }

    public Vente effectuerVente(Client client, Medicament medicament, int quantite) {
        if (medicament.getStock() >= quantite) {
            double montant = quantite * medicament.getPrix();
            medicament.setStock(medicament.getStock() - quantite);
            Vente vente = new Vente(new Date(), montant);
            System.out.println("Vente effectuée par " + nom + " à " + client.getNom() + " pour " + montant + "€.");
            vente.enregistrerVente();
            return vente;
        } else {
            System.out.println("Stock insuffisant pour " + medicament.getNom());
            return null;
        }
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
