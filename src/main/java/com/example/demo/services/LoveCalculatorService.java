package com.example.demo.services;

import com.example.demo.api.LoveCalculatorApi;
import com.example.demo.api.LoveResult;
import com.example.demo.configuration.DbConfiguration;
import com.example.demo.dto.LoveHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoveCalculatorService {

    @Autowired
    private DbConfiguration dbConfiguration;

    @Autowired
    private LoveCalculatorApi loveCalculatorApi;

    private Connection connection;

    public LoveResult testApi(String firstName, String secondName) {
        return this.loveCalculatorApi.bringMeLove(firstName, secondName);
    }

    /**
     * Cours #1
     *
     * Initialiser la connexion à la base de données
     * grace aux paramètres de configuration {@link DbConfiguration}
     *
     */
    @PostConstruct
    public void init() throws SQLException {
    }

    /**
     * Cours #2
     *
     * Retourner la liste de tous les noms de la base de données.
     *
     * Test
     * - http://localhost:8080/names
     *
     */
    public List<String> getAllNames() throws SQLException {
        return null;
    }

    /**
     * TD #1
     *
     * A partir de l'exemple ci-dessus, retourner une liste de @{@link LoveHistory}
     * représentant tout l'historique contenant pour chaque ligne les noms des deux personnes
     * et leur résultat.
     *
     * Aide
     * - Des jointures sql seront nécessaires pour lier les tables entre-elles.
     *
     * Test
     * - http://localhost:8080/history
     *
     * =======
     *
     * TD #2
     *
     * Améliorer votre code pour retourner tout l'historique si le paramètre 'name'
     * n'est pas présent, et l'historique filtré pour une seule personne si le paramètre 'name'
     * est présent.
     *
     * Aide
     * - Rechercher sur internet comment utiliser @{@link PreparedStatement}.
     * - Vérifier la présence du paramètre 'name' par 'name.isPresent()'
     * - Récupérer la valeur de 'name' par 'name.get()'
     * - une clause sql where sera nécessaire pour filtrer les noms.
     *
     * Test
     * - http://localhost:8080/history?name=links
     *
     */
    public List<LoveHistory> getHistory(Optional<String> name) throws SQLException {
        return null;
    }

    /**
     * TD #3
     *
     * En utilisant les deux paramètres 'firstname' et 'secondname' :
     * - appeler le {@link LoveCalculatorApi} pour calculer le résultat
     * - puis insérer le résultat dans l'historique de la base de données
     * - enfin retourner un {@link LoveHistory} contenant les deux noms et le résultat
     *
     * /!\ Attention !
     * Le nom est défini comme étant unique dans la table 'names'.
     * Si le nom n'existe pas en base, il devra etre insérer avant l'insertion de l'historique
     * pour lui attribuer un id.
     *
     * Aide
     * - Rechercher sur internet comment récupérer le nombre de ligne d'un {@link ResultSet}
     * - Si le nombre de ligne est = 0, la requete n'a trouvé aucun résultat.
     * - Rechercher sur internet comment récupérer l'id d'une ligne créée : Statement.RETURN_GENERATED_KEYS
     * - Factoriser votre code au maximum afin d'optimiser votre algorithme.
     *
     * Test
     * - http://localhost:8080/love?firstname=mario&secondname=peach
     *
     */
    public LoveHistory love(String firstname, String secondname) throws SQLException {
        return null;
    }

}
