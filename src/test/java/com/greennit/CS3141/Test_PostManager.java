package com.greennit.CS3141;

import com.greennit.CS3141.entities.Post;
import com.greennit.CS3141.managers.PostManager;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class Test_PostManager {

    Post post;
    PostManager manager;

    //post(3, 1, qcross, contentTest, 2021-3-22 03:31:45, 0, 3) in database

    @Before
    public void start(){
        manager = new PostManager();
    }

    @Test
    public void getPost(){
        Timestamp timestamp = Timestamp.valueOf("2021-3-22 03:31:45");
        post = manager.getPost(3);
        assertEquals(1,post.getHost_thread());
        assertEquals(3, post.getId());
        assertEquals("qcross", post.getAuthor());
        assertEquals("contentTest", post.getContent());
        assertEquals(timestamp, post.getTime_posted()); //Returns wrong time, off by 3 hours
        assertEquals(0, post.getLikes());
        assertEquals(3, post.getParent_id());
    }

    @Test
    public void createPost(){
        manager.createPost(1, "qcross", "content", 1);
        post = manager.getPost(2);
        
    }

    public static void main(String[] args) {
        PostManager postManager = new PostManager();
        postManager.createPost(1, "qcross", "contentTest", 1);
    }

}
