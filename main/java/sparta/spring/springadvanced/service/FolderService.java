package sparta.spring.springadvanced.service;

import sparta.spring.springadvanced.model.Folder;
import sparta.spring.springadvanced.model.User;
import sparta.spring.springadvanced.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    // 로그인한 회원에 폴더들 등록
    public List<Folder> addFolders(List<String> folderNames, User user) {
        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            Folder folder = new Folder(folderName, user);
            folderList.add(folder);
        }

        return folderRepository.saveAll(folderList);
    }

    // 로그인한 회원이 등록된 모든 폴더 조회
    public List<Folder> getFolders(User user) {
        return folderRepository.findAllByUser(user);
    }
}
