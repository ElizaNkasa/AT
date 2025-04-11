public class Medicament {
    private int id;
    private String nom;
    private double prix;
    private String fabricant;
    private int stock;

    public Medicament(int id, String nom, double prix, String fabricant, int stock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.fabricant = fabricant;
        this.stock = stock;
    }

    public void afficherInfos() {
        System.out.println("Médicament: " + nom + ", Prix: " + prix + "€, Stock: " + stock + ", Fabricant: " + fabricant);
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public String getFabricant() { return fabricant; }
    public int getStock() { return stock; }

    // Setters
    public void setStock(int stock) { this.stock = stock; }
}
