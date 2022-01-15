package gq.vantex.prison.profile;

import com.mongodb.client.model.Filters;
import gq.vantex.prison.Prison;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Profile {

    private final UUID uuid;
    private String username;
    private List<String> enchants;

    public void save(boolean async) {

        if (async) {
            new Thread(() -> save(false)).start();
            return;
        }

        Document old = Prison.getInstance().getMongoDB().getProfiles().find(Filters.eq("uuid", uuid.toString())).first();

        if (old == null) {
            Prison.getInstance().getLogger().warning("Unable to save profile with UUID of " + uuid + " - old document not found.");
            return;
        }

        Prison.getInstance().getMongoDB().getProfiles().replaceOne(old, Document.parse(Prison.GSON.toJson(this)));

    }

}
