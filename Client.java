public class Client {
    private String nom;
    private String adresse;
    private String telephone;

    public Client(String nom, String adresse, String telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public void effectuerAchat(Medicament medicament, int quantite) {
        if (medicament.getStock() >= quantite) {
            medicament.setStock(medicament.getStock() - quantite);
            double total = quantite * medicament.getPrix();
            System.out.println(nom + " a acheté " + quantite + " unités de " + medicament.getNom() + " pour " + total + "€.");
        } else {
            System.out.println("Stock insuffisant pour " + medicament.getNom());
        }
    }

    // Getters
    public String getNom() {
        return nom;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
