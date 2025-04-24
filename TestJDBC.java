public class TestJDBC {
    public static void main(String[] args) {
        // TEST ClientDAO
        ClientDAO clientDAO = new ClientDAO();
        Client nouveauClient = new Client("Ali", "123 rue du code", "0612345678");
        clientDAO.ajouterClient(nouveauClient);

        System.out.println("\n--- Liste des clients ---");
        for (Client c : clientDAO.getTousLesClients()) {
            System.out.println(c.getNom() + " - " + c.getAdresse() + " - " + c.getTelephone());
        }

        // TEST MedicamentDAO
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        Medicament doliprane = new MedicamentVenteLibre(1, "Doliprane", 2.5, "Sanofi", 50, "Fièvre, douleurs");
        medicamentDAO.ajouterMedicament(doliprane, "vente_libre", "Fièvre, douleurs", null, null);

        Medicament amoxicilline = new MedicamentAOrdonnance(2, "Amoxicilline", 10.0, "Biogaran", 20, "500mg", "Allergie à la pénicilline");
        medicamentDAO.ajouterMedicament(amoxicilline, "ordonnance", null, "500mg", "Allergie à la pénicilline");

        System.out.println("\n--- Liste des médicaments ---");
        for (Medicament m : medicamentDAO.getTousLesMedicaments()) {
            m.afficherInfos();
            System.out.println();
        }

        // Fermer proprement la connexion
        DatabaseManager.closeConnection();
    }
}