package com.Journal011.journal11.repository;
import com.Journal011.journal11.entity.JournalEntry;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}