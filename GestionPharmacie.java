import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;


// Classe principale de gestion de la pharmacie
public class GestionPharmacie {
    private static List<Medicament> medicaments = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    private static List<Vente> ventes = new ArrayList<>();
    private static Pharmacien pharmacien = new Pharmacien("Jean Dupont", 1, "12 rue de la pharmacie", "0600000000");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        chargerMedicaments();
        menuConnexion();
    }

    public static void menuConnexion() {
        int choix;
        do {
            System.out.println("\n=== Connexion ===");
            System.out.println("1. Se connecter en tant que client");
            System.out.println("2. Se connecter en tant que pharmacien");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            while (!scanner.hasNextInt()) {
                System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
                scanner.next();
            }

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 : menuClient();
                case 2 :menuPharmacien();
                case 0 : System.out.println("Au revoir !");
                default : System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    public static void menuClient() {
        int choix;
        do {
            System.out.println("\n=== Menu Client ===");
            System.out.println("1. Voir les médicaments disponibles");
            System.out.println("2. Effectuer un achat");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            while (!scanner.hasNextInt()) {
                System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
                scanner.next();
            }

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 :afficherMedicaments();
                case 2 : effectuerVente();
                case 0 : System.out.println("Retour au menu principal.");
                default : System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    public static void menuPharmacien() {
        int choix;
        do {
            System.out.println("\n=== Menu Pharmacien ===");
            System.out.println("1. Ajouter un médicament");
            System.out.println("2. Afficher les médicaments");
            System.out.println("3. Ajouter un client");
            System.out.println("4. Effectuer une vente");
            System.out.println("5. Afficher les ventes");
            System.out.println("6. Rechercher un médicament par ID");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            while (!scanner.hasNextInt()) {
                System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
                scanner.next();
            }

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 : ajouterMedicament();
                case 2 : afficherMedicaments();
                case 3 : ajouterClient();
                case 4 : effectuerVente();
                case 5 : afficherVentes();
                case 6 : rechercherMedicamentParId();
                case 0 : System.out.println("Retour au menu principal.");
                default : System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    public static void initialiserMedicaments() {
        medicaments.add(new MedicamentVenteLibre(1, "Doliprane", 2.5, "Sanofi", 50, "Maux de tête"));
        medicaments.add(new MedicamentAOrdonnance(2, "Amoxicilline", 10, "Biogaran", 20, "500mg", "Allergie à la pénicilline"));
        medicaments.add(new MedicamentAOrdonnance(3, "Quininit", 15, "Biogaran", 100, "10mg", "Pour la fièvre"));
        medicaments.add(new MedicamentAOrdonnance(4, "Vircis", 1, "Shalina", 150, "30mg", "La douleur"));
        medicaments.add(new MedicamentAOrdonnance(5, "Paracétamol", 300, "Shalina", 5000, "10mg", "La fièvre"));
    }

    public static void afficherMedicaments() {
        System.out.println("\n--- Liste des médicaments ---");
        for (Medicament m : medicaments) {
            m.afficherInfos();
            System.out.println();
        }
    }

    public static void ajouterClient() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        clients.add(new Client(nom, adresse, telephone));
        System.out.println("Client ajouté avec succès.");
    }

    public static void effectuerVente() {
        if (clients.isEmpty()) {
            System.out.println("Aucun client. Veuillez d'abord en ajouter.");
            return;
        }

        System.out.println("\nSélectionner un client :");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i).getNom());
        }
        System.out.print("Votre choix : ");

        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
            scanner.next();
        }

        int indexClient = scanner.nextInt() - 1;
        scanner.nextLine();

        if (indexClient < 0 || indexClient >= clients.size()) {
            System.out.println("Index de client invalide.");
            return;
        }

        Client client = clients.get(indexClient);

        afficherMedicaments();
        System.out.print("ID du médicament : ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
            scanner.next();
        }

        int idMed = scanner.nextInt();
        System.out.print("Quantité : ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
            scanner.next();
        }

        int quantite = scanner.nextInt();
        scanner.nextLine();

        Medicament medicamentChoisi = null;
        for (Medicament m : medicaments) {
            if (m.getId() == idMed) {
                medicamentChoisi = m;
                break;
            }
        }

        if (medicamentChoisi != null) {
            Vente vente = pharmacien.effectuerVente(client, medicamentChoisi, quantite);
            if (vente != null) {
                ventes.add(vente);
                System.out.println("Vente effectuée avec succès.");
            }
        } else {
            System.out.println("Médicament non trouvé.");
        }
    }

    public static void afficherVentes() {
        if (ventes.isEmpty()) {
            System.out.println("Aucune vente enregistrée.");
        } else {
            System.out.println("\n--- Historique des ventes ---");
            for (Vente v : ventes) {
                System.out.println("Vente ID: " + v.getIdVente() +
                        ", Date: " + v.getDateVente() +
                        ", Montant: " + v.getMontantTotal() + "€");
            }
        }
    }

    public static void ajouterMedicament() {
        System.out.println("\nQuel type de médicament souhaitez-vous ajouter ?");
        System.out.println("1. Vente libre");
        System.out.println("2. À ordonnance");
        System.out.print("Votre choix : ");

        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide. Veuillez entrer 1 ou 2 : ");
            scanner.next();
        }

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nom du médicament : ");
        String nom = scanner.nextLine();
        System.out.print("Prix : ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Entrée invalide. Veuillez entrer un prix valide : ");
            scanner.next();
        }

        double prix = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Fabricant : ");
        String fabricant = scanner.nextLine();
        System.out.print("Stock : ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide. Veuillez entrer une quantité valide : ");
            scanner.next();
        }

        int stock = scanner.nextInt();
        scanner.nextLine();

        int id = medicaments.size() + 1;

        if (type == 1) {
            System.out.print("Indication : ");
            String indication = scanner.nextLine();
            medicaments.add(new MedicamentVenteLibre(id, nom, prix, fabricant, stock, indication));
        } else if (type == 2) {
            System.out.print("Dosage : ");
            String dosage = scanner.nextLine();
            System.out.print("Contre-indication : ");
            String contreIndication = scanner.nextLine();
            medicaments.add(new MedicamentAOrdonnance(id, nom, prix, fabricant, stock, dosage, contreIndication));
        } else {
            System.out.println("Type de médicament invalide.");
        }

        System.out.println("Médicament ajouté avec succès !");
    }

    public static void rechercherMedicamentParId() {
        System.out.print("Entrez l'ID du médicament à rechercher : ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
            scanner.next();
        }

        int idRecherche = scanner.nextInt();
        scanner.nextLine();

        boolean trouve = false;
        for (Medicament m : medicaments) {
            if (m.getId() == idRecherche) {
                System.out.println("Médicament trouvé :");
                m.afficherInfos();
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            System.out.println("Aucun médicament trouvé avec l'ID " + idRecherche);
        }
    }

    public static void sauvegarderMedicaments() {
        try (Writer writer = new FileWriter("medicaments.json")) {
            Fichier<Medicament> typeFactory =
                Fichier.of(Medicament.class, "type")
                .registerSubtype(MedicamentVenteLibre.class)
                .registerSubtype(MedicamentAOrdonnance.class);

            Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(typeFactory)
                .setPrettyPrinting()
                .create();

            gson.toJson(medicaments, writer);
            System.out.println("Médicaments sauvegardés dans medicaments.json.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void chargerMedicaments() {
        try (Reader reader = new FileReader("medicaments.json")) {
            Fichier<Medicament> typeFactory =
                Fichier.of(Medicament.class, "type")
                .registerSubtype(MedicamentVenteLibre.class)
                .registerSubtype(MedicamentAOrdonnance.class);

            Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(typeFactory)
                .create();

            Type type = new TypeToken<List<Medicament>>(){}.getType();
            medicaments = gson.fromJson(reader, type);
            System.out.println("Médicaments chargés depuis medicaments.json.");
        } catch (FileNotFoundException e) {
            System.out.println("Fichier JSON introuvable. Initialisation par défaut.");
            chargerMedicaments();
            sauvegarderMedicaments();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}