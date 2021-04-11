package com.greennit.CS3141;

import com.greennit.CS3141.entities.Thread;
import com.greennit.CS3141.managers.ThreadManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_ThreadManager {

    Thread thread;
    ThreadManager manager;
    int ID = 19; // MUST BE UPDATED EVERY TEST. WORKING ON SOLUTION. CURRENT 17

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void start() {
        manager = new ThreadManager();
    }

    @Test
    public void checkAllFieldsExist() {
        thread = manager.getThread(1);
        assertEquals(1, thread.getHost());
        assertEquals(1, thread.getId());
        assertEquals("test", thread.getTitle());
        assertEquals("qcross", thread.getAuthor());
        assertEquals("testcontent", thread.getContent());
        assertEquals("2021-03-07 14:16:54.0", thread.getCreation_date().toString()); // Only works for EST
    }

    @Test()
    public void checkInsertAndDelete() {
        thread = manager.createThread(1,"test2", "qcross", "testcontent2", Timestamp.valueOf("2000-03-03 12:00:00.0"));
        int ID = thread.getId();
        assertEquals(1, thread.getHost());
        assertEquals(ID, thread.getId());
        assertEquals("test2", thread.getTitle());
        assertEquals("qcross", thread.getAuthor());
        assertEquals("testcontent2", thread.getContent());
        assertEquals("2000-03-03 12:00:00.0", thread.getCreation_date().toString()); // Only works for EST

        manager.deleteThread(ID);

        exception.expect(IllegalArgumentException.class);
        thread = manager.getThread(ID);
    }

    @Test
    public void checkGetThreads() {
        List<Thread> threads = manager.getThreadsBySubgreennit(1);

        for (Thread thread : threads) {
            assertEquals(1, thread.getId());
            assertEquals(1, thread.getHost());
            assertEquals("test", thread.getTitle());
            assertEquals("qcross", thread.getAuthor());
            assertEquals("testcontent", thread.getContent());
            assertEquals("2021-03-07 14:16:54.0", thread.getCreation_date().toString());
        }
    }

    @Test
    public void updateThreadTests(){
        manager.updateThreadContent(1, "updateTest");
        thread = manager.getThread(1);
        assertEquals(thread.getId(), 1);
        assertEquals("updateTest", thread.getContent());
        manager.updateThreadContent(1, "testcontent");
    }

    @Test
    public void errorTest(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Host Subgreennit or Thread ID provided not valid.");
        manager.getThread(-1);
    }

}
