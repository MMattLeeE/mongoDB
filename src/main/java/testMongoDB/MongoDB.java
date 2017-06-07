package testMongoDB;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by Matt on 6/5/2017.
 */
public class MongoDB {

    private static MongoClient mongoDatabaseConnection;

    private static String dbname = "userDB";

    private static String collectionName = "users";

    public static void setMongoDatabaseConnection() {
        try {
            MongoDB.mongoDatabaseConnection = new MongoClient(new ServerAddress("Localhost", 27017));
            System.out.println("MongoDB connection created to localhost and port 27017");
        } catch (UnknownHostException ex) {
            System.err.println("cant find the host cant connect");
        }
    }

    //method to add a user to the mongoDB
    public static void addUserToMongoDB(User user) {
        DB database = mongoDatabaseConnection.getDB(dbname);
        DBCollection collection = database.getCollection(collectionName);
        DBObject document = new BasicDBObject("firstName",user.getFirstName())
                    .append("lastName",user.getLastName())
                    .append("ssn",user.getSsn())
                    .append("dob",user.getDob())
                    .append("gender",user.getGender())
                    .append("username",user.getUsername())
                    .append("password",user.getPassword())
                    .append("email",user.getEmail())
                    .append("phoneNumber",user.getPhoneNumber())
                    .append("photoPath",user.getProfilePhoto());
        collection.insert(document);
    }

    //Returns a cursor pointing to the user based on the field value matching
    private static DBCursor getUserCursor(String field, String value) {
        DB database = MongoDB.mongoDatabaseConnection.getDB(dbname);
        DBCollection collection = database.getCollection(collectionName);
        DBCursor userCursor = collection.find(new BasicDBObject(field,value));

        return userCursor;
    }

    //get a user by username
    public static User getUser(String username){
        DBCursor cursor = getUserCursor("username", username);
        User user = null;
        DBObject userDbo;

        while (cursor.hasNext()) {
            userDbo = cursor.next();
            user = new User((String) userDbo.get("firstName"),
                    (String) userDbo.get("lastName"),
                    (String) userDbo.get("ssn"),
                    (String) userDbo.get("dob"),
                    (String) userDbo.get("gender"),
                    (String) userDbo.get("username"),
                    (String) userDbo.get("password"),
                    (String) userDbo.get("email"),
                    (String) userDbo.get("phoneNumber"),
                    (String) userDbo.get("photoPath"));
        }
        return user;
    }

    public static boolean doesUsernameExist(String username) {
        DBCursor user = getUserCursor("username",username);
        boolean usernameExists=false;

        if (user.size() >= 1) {
            usernameExists = true;
        } else if (user.size()==0) {
            usernameExists = false;
        }
        return usernameExists;
    }

}
