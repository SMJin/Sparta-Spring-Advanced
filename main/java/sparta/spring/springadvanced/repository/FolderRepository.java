package sparta.spring.springadvanced.repository;

import sparta.spring.springadvanced.model.Folder;
import sparta.spring.springadvanced.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);
}