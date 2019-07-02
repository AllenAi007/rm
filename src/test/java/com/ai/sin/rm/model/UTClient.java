package com.ai.sin.rm.model;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test Client POJO
 */
public class UTClient {

    @Test
    public void testClientEquals() {
        Client client = new Client();
        client.setId(1);
        client.setName("Allen");
        client.setRisk(3);
        assertFalse(client.equals(null));
        assertTrue(client.equals(client));

        Client sameClient = new Client();
        sameClient.setId(1);
        sameClient.setName("Allen");
        sameClient.setRisk(3);
        assertTrue(client.equals(sameClient));

        Client anotherClient = new Client();
        anotherClient.setId(2);
        anotherClient.setName("Other");
        anotherClient.setRisk(3);
        assertFalse(client.equals(anotherClient));

    }

    @Test
    public void testHashCode() {
        Client client = new Client();
        client.setId(1);
        client.setName("Allen");
        client.setRisk(3);
        assertNotNull(client.hashCode());

        Client sameClient = new Client();
        sameClient.setId(1);
        sameClient.setName("Allen");
        sameClient.setRisk(3);
        assertTrue(client.hashCode() == sameClient.hashCode());

        Client anotherClient = new Client();
        anotherClient.setId(2);
        anotherClient.setName("Other");
        anotherClient.setRisk(3);
        assertFalse(client.hashCode() == anotherClient.hashCode());
    }
}
