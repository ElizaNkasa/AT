import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDAO {

    public void ajouterMedicament(Medicament medicament, String type,
                                  String indication, String dosage, String contreIndication) {
        String sql = "INSERT INTO medicament (id, nom, prix, fabricant, stock, type, indications, dosage, contre_indication) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, medicament.getId());
            stmt.setString(2, medicament.getNom());
            stmt.setDouble(3, medicament.getPrix());
            stmt.setString(4, medicament.getFabricant());
            stmt.setInt(5, medicament.getStock());
            stmt.setString(6, type);
            stmt.setString(7, indication);         // null pour ordonnance
            stmt.setString(8, dosage);             // null pour vente libre
            stmt.setString(9, contreIndication);   // null pour vente libre

            stmt.executeUpdate();
            System.out.println("Médicament ajouté à la base.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medicament> getTousLesMedicaments() {
        List<Medicament> medicaments = new ArrayList<>();
        String sql = "SELECT * FROM medicament";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                String fabricant = rs.getString("fabricant");
                int stock = rs.getInt("stock");
                String type = rs.getString("type");

                if ("vente_libre".equalsIgnoreCase(type)) {
                    String indication = rs.getString("indications");
                    medicaments.add(new MedicamentVenteLibre(id, nom, prix, fabricant, stock, indication));
                } else if ("ordonnance".equalsIgnoreCase(type)) {
                    String dosage = rs.getString("dosage");
                    String contreIndication = rs.getString("contre_indication");
                    medicaments.add(new MedicamentAOrdonnance(id, nom, prix, fabricant, stock, dosage, contreIndication));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;
    }
}