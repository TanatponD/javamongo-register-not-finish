import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class Mongolabjava {
    public static void main(String[] args) {

        MongoClientURI uri = new MongoClientURI("mongodb://<dbuser>:<dbpassword>@ds133762.mlab.com:33762/javamongo");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());

        List<Document> seedData = new ArrayList<>();




        seedData.add(new Document("decade", "1970s")
                .append("artist", "Debby Boone")
                .append("song", "You Light Up My Life")
                .append("weeksAtOne", 10)
        );

        seedData.add(new Document("decade", "1980s")
                .append("artist", "Olivia Newton-John")
                .append("song", "Physical")
                .append("weeksAtOne", 10)
        );

        seedData.add(new Document("decade", "1990s")
                .append("artist", "Mariah Carey")
                .append("song", "One Sweet Day")
                .append("weeksAtOne", 16)
        );


        MongoCollection<Document> songs = db.getCollection("songs");

        songs.insertMany(seedData);


        Document updateQuery = new Document("song", "One Sweet Day");
        songs.updateOne(updateQuery, new Document("$set", new Document("artist", "Mariah Carey ft. Boyz II Men")));
        Document findQuery = new Document("weeksAtOne", new Document("$gte",10));
        Document orderBy = new Document("decade", 1);

        MongoCursor<Document> cursor = songs.find(findQuery).sort(orderBy).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(
                        "In the " + doc.get("decade") + ", " + doc.get("song") +
                                " by " + doc.get("artist") + " topped the charts for " +
                                doc.get("weeksAtOne") + " straight weeks."
                );
            }
        } finally {
            cursor.close();
        }

        // Since this is an example, we'll clean up after ourselves.


        // Only close the connection when your app is terminating

        client.close();
    }
    }
