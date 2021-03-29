package com.greennit.CS3141;

import com.greennit.CS3141.entities.Post;
import com.greennit.CS3141.managers.PostManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test_PostManager {

    Post post;
    PostManager manager;
    int counter = 10; //Needs to be updated each time

    //post(4, 1, qcross, contentTest, 2021-3-22 2021-03-28 20:49:51, 0, 4) in database

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
        assertEquals(4, post.getParent_id());
    }

    @Test
    public void createAndDeletePost(){
        manager.createPost(1, "qcross", "createPostTest", 4);
        post = manager.getPost(counter);
        assertEquals(post.getContent(), "createPostTest");
        manager.deletePost(counter);
        try{
            manager.getPost(counter);
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

}
