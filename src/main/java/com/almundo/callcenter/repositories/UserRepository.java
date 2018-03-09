package com.almundo.callcenter.repositories;

import com.almundo.callcenter.entities.User;
import com.almundo.callcenter.utils.UserTypesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(User user) {
        mongoTemplate.insert(user);
    }

    public List<User> getAll() {
        return mongoTemplate.findAll(User.class);
    }

    public User findById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public void save(User user) {
        mongoTemplate.save(user);
    }

    //TODO: take out repetitive logic in findAvailable() to a loop
    public User findAvailable() {
        Query query = this.vailableCriteriaBiulder(UserTypesEnum.OPERADOR);
        User user = mongoTemplate.findOne(query, User.class);

        if (user == null){
            query = this.vailableCriteriaBiulder(UserTypesEnum.SUPERVISOR);
            user = mongoTemplate.findOne(query, User.class);
        }

        if (user == null){
            query = this.vailableCriteriaBiulder(UserTypesEnum.DIRECTOR);
            user = mongoTemplate.findOne(query, User.class);
        }

        return user;
    }

    private Query vailableCriteriaBiulder(UserTypesEnum userType){
        Query query = new Query();
        query.addCriteria(Criteria.where("currentCall").is(null));
        query.addCriteria(Criteria.where("type").is(userType.getType()));
        return query;
    }
}
