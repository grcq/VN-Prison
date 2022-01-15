package gq.vantex.prison.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gq.vantex.prison.Prison;
import lombok.Getter;
import org.bson.Document;

public class MongoDB {

    private MongoClient client;
    private MongoDatabase database;

    @Getter private MongoCollection<Document> profiles;

    public MongoDB load() {

        this.client = MongoClients.create("");

        try {
            this.database = client.getDatabase("Prison");

            this.profiles = database.getCollection("profiles");
        } catch (Exception e) {
            Prison.getInstance().getLogger().warning("MongoDB failed to load.");
        }

        return this;
    }

}
