package net.algoreddy.JournalApp.repository;

import net.algoreddy.JournalApp.entity.JournalEntry;
import net.algoreddy.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
   User findByUserName(String userName);
}
