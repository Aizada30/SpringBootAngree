package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.User;

public interface UserRepo extends JpaRepository<User ,Long > {


}
