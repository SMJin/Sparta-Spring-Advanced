package sparta.spring.springadvanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.spring.springadvanced.model.Folder;
import sparta.spring.springadvanced.model.User;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);
    List<Folder> findAllByUserAndNameIn(User user, List<String> names);
}