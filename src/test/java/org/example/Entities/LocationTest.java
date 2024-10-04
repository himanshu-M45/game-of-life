package org.example.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    void TestLocationInitialization() {
        Location location = new Location(4,5);
        assertNotNull(location);
    }

    @Test
    void TestIsSameLocation() {
        Location location = new Location(4,5);
        assertTrue(location.isSameLocation(4,5));
    }
}