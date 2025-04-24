import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VenteDAO {

    public void ajouterVente(Vente vente, int clientId, int medicamentId, int quantite) {
        String sql = "INSERT INTO vente (client_id, medicament_id, quantite, montant_total, date_vente) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            stmt.setInt(2, medicamentId);
            stmt.setInt(3, quantite);
            stmt.setDouble(4, vente.getMontantTotal());
            stmt.setTimestamp(5, new Timestamp(vente.getDateVente().getTime()));

            stmt.executeUpdate();
            System.out.println("Vente enregistrée.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerVente(int idVente) {
        String sql = "DELETE FROM vente WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVente);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Vente supprimée.");
            } else {
                System.out.println("Aucune vente trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mettreAJourVente(int idVente, double montant, int quantite) {
        String sql = "UPDATE vente SET montant_total = ?, quantite = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, montant);
            stmt.setInt(2, quantite);
            stmt.setInt(3, idVente);
            stmt.executeUpdate();
            System.out.println("Vente mise à jour.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vente> getToutesLesVentes() {
        List<Vente> ventes = new ArrayList<>();
        String sql = "SELECT * FROM vente";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                double montant = rs.getDouble("montant_total");
                Date date = new Date(rs.getTimestamp("date_vente").getTime());
                Vente vente = new Vente(date, montant);
                ventes.add(vente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventes;
    }
}