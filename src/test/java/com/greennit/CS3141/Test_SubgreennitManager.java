package com.greennit.CS3141;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.managers.SubgreennitManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_SubgreennitManager {

    Subgreennit subgreennit;
    SubgreennitManager manager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void start() {
        manager = new SubgreennitManager();
    }

    @Test
    public void checkAllFieldsExist() {
        subgreennit = manager.getSubgreennit(1);
        assertEquals(1, subgreennit.getId());
        assertEquals("test", subgreennit.getName());
        assertEquals("abc", subgreennit.getDescription());
    }

    @Test()
    public void checkInsertAndDelete() {
        subgreennit = manager.createSubgreennit("tempName","tempDesc");
        int ID = subgreennit.getId();
        assertEquals(ID, subgreennit.getId());
        assertEquals("tempName", subgreennit.getName());
        assertEquals("tempDesc", subgreennit.getDescription());

        manager.deleteSubgreennit(ID);

        exception.expect(IllegalArgumentException.class);
        subgreennit = manager.getSubgreennit(ID);
    }

    @Test
    public void checkGetSubgreennits() {
        List<Subgreennit> subgreennits = manager.getSubgreennits("id=1");

        for (Subgreennit subgreennit : subgreennits) {
            assertEquals(1, subgreennit.getId());
            assertEquals("test", subgreennit.getName());
            assertEquals("abc", subgreennit.getDescription());
        }
    }

    @Test
    public void updateSubgreennitTests(){
        manager.updateDescription(1, "new");
        subgreennit = manager.getSubgreennit(1);
        assertEquals(1, subgreennit.getId());
        assertEquals("new", subgreennit.getDescription());
        manager.updateDescription(1, "abc");
    }

    @Test
    public void errorTest(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Subgreennit ID provided not valid.");
        manager.getSubgreennit(-1);
    }
}
