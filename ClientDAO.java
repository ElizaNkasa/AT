import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void ajouterClient(Client client) {
        String sql = "INSERT INTO client (nom, adresse, telephone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdresse());
            stmt.setString(3, client.getTelephone());
            stmt.executeUpdate();
            System.out.println("Client ajouté en base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerClient(int id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Client supprimé.");
            } else {
                System.out.println("Aucun client trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mettreAJourClient(Client client, int id) {
        String sql = "UPDATE client SET nom = ?, adresse = ?, telephone = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdresse());
            stmt.setString(3, client.getTelephone());
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Client mis à jour.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getTousLesClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                Client client = new Client(nom, adresse, telephone);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}