package com.almundo.callcenter.repositories;

import com.almundo.callcenter.entities.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CallRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Call insert(Call call) {
        mongoTemplate.insert(call);
        return call;
    }

    public List<Call> getAll() {
        return mongoTemplate.findAll(Call.class);
    }

    public Call findById(String id) {
        return mongoTemplate.findById(id, Call.class);
    }

    public void save(Call call) {
        mongoTemplate.save(call);
    }
}
