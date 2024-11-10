package micro.landmates.users_microservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import micro.landmates.users_microservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
