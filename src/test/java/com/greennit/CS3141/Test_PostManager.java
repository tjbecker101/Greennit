package com.greennit.CS3141;

import com.greennit.CS3141.entities.Post;
import com.greennit.CS3141.managers.PostManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Test_PostManager {

    Post post;
    PostManager manager;

    //post(4, 1, qcross, contentTest, 2021-3-22 2021-03-28 20:49:51, 0, 4) in database

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void start(){
        manager = new PostManager();
    }

    @Test
    public void getPost(){
        //Timestamp timestamp = Timestamp.valueOf("2021-3-29 20:49:51"); //Timezone is different
        post = manager.getPost(4);
        assertEquals(1,post.getHost_thread());
        assertEquals(4, post.getId());
        assertEquals("qcross", post.getAuthor());
        assertEquals("contentTest", post.getContent());
        //assertEquals(timestamp, post.getTime_posted()); //Returns wrong time, off by 3 hours
        assertEquals(15, post.getLikes());
    }

    @Test
    public void createAndDeletePost(){
        post = manager.createPost(1, "qcross", "createPostTest");
        assertEquals(post.getContent(), "createPostTest");
        manager.deletePost(post.getId());
        try{
            manager.getPost(post.getId());
        }catch(IllegalArgumentException ex){
            assertEquals("Host Thread or Post ID provided not valid.", ex.getMessage());
        }
    }

    @Test
    public void updatePost(){
        manager.updatePostContent(4, "updateTest");
        post = manager.getPost(4);
        assertEquals(post.getContent(),"updateTest");
        manager.updatePostContent(4, "contentTest");
    }

    @Test
    public void postsByThread(){
        List<Post> posts = manager.getPostsByThread(1);
        for(Post post : posts){
            Assertions.assertEquals(4, post.getId());
            Assertions.assertEquals(1, post.getHost_thread());
            Assertions.assertEquals("qcross", post.getAuthor());
            Assertions.assertEquals("contentTest", post.getContent());
            Assertions.assertEquals(15, post.getLikes());
            Assertions.assertEquals("2021-04-04 14:59:17.0", post.getTime_posted().toString());

        }
    }

    @Test
    public void errorTest(){
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Host Thread or Post ID provided not valid.");
        manager.getPost(-1);
    }

}
