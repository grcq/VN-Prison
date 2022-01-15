package gq.vantex.prison.profile;

import com.mongodb.client.model.Filters;
import gq.vantex.prison.Prison;
import lombok.SneakyThrows;
import org.bson.Document;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ProfileManager {

    @SneakyThrows
    public Profile getProfile(UUID uuid) {
        return (Profile) CompletableFuture.supplyAsync((Supplier<Object>) () -> {
            Document document = Prison.getInstance().getMongoDB().getProfiles().find(Filters.eq("uuid", uuid.toString())).first();
            return (document == null ? null : Prison.GSON.fromJson(document.toJson(), Profile.class));
        }).get();
    }

    public Profile createProfile(UUID uuid, String username) {
        Profile profile = new Profile(uuid, username, new ArrayList<>());

        Document document = Document.parse(Prison.GSON.toJson(profile));
        Prison.getInstance().getMongoDB().getProfiles().insertOne(document);

        return profile;
    }

}
