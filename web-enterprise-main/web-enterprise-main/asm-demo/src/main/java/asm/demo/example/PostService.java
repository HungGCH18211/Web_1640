package asm.demo.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class PostService {
 
    @Autowired
    private PostRepository repo;
     
    public List<Post> listAll() {
        return repo.findAll();
    }
     
    public void save(Post post) {
        repo.save(post);
    }
     
    public Post get(long idpost) {
        return repo.findById(idpost).get();
    }
     
    public void delete(long idpost) {
        repo.deleteById(idpost);
    }
}
