package ru.kpfu.itis.cmsforblogs.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.cmsforblogs.dto.CreatePostDto;
import ru.kpfu.itis.cmsforblogs.dto.KafkaNotificationMessage;
import ru.kpfu.itis.cmsforblogs.entity.Post;
import ru.kpfu.itis.cmsforblogs.entity.PostMaterial;
import ru.kpfu.itis.cmsforblogs.entity.User;
import ru.kpfu.itis.cmsforblogs.repository.PostRepository;
import ru.kpfu.itis.cmsforblogs.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class BlogService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final MinioService minioService;
    private final NotificationProducer notificationProducer;

    public void createBlog(String username, CreatePostDto dto){
        User user = userRepository.findByUsername(username).orElseThrow(
                ()->new RuntimeException("User not found")
        );

        Post post = Post.builder()
                .title(dto.getTitle())
                .text(dto.getText())
                .materials(new ArrayList<PostMaterial>())
                .build();
        postRepository.save(post);
        user.addPost(post);
        userRepository.save(user);
        for (int i = 0; i < dto.getFiles().length; i++){
            MultipartFile file = dto.getFiles()[i];
            String filename = String.format("%s-%d-%d", username, post.getId(), i);
            minioService.uploadFile(file, filename);
            PostMaterial postMaterial = PostMaterial.builder()
                    .type(getType(file.getOriginalFilename()))
                    .nameInMinio(filename)
                    .build();
            post.addMaterial(postMaterial);
        }
        postRepository.save(post);
        sendMessages(post.getId());
    }

    private String getType(String filename){
        if (reg(".*\\.(png|jpg|jpeg)", filename)) return "img";
        if (reg(".*\\.(mp4|avi|mov)", filename)) return "vid";
        return "file";

    }

    private boolean reg(String reg, String s){
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public List<Post> getMyBlogs(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                ()->new RuntimeException("User not found")
        );
        return postRepository.findAllByUser(user);
    }

    public List<Post> getBlogs() {
        return postRepository.findNew();
    }

    public List<Post> getBlogsById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new RuntimeException("User not found")
        );
        return postRepository.findAllByUser(user);
    }

    public void sendMessages(Long postId){
        //какой-то список пользователей
        List<User> consumers = userRepository.findAll();
        for (User user:consumers){
            notificationProducer.sendMessage(KafkaNotificationMessage.builder()
                            .consumerUsername(user.getUsername())
                            .newPostId(postId)
                    .build());
        }
    }
}
