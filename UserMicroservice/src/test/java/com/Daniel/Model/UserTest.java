package com.Daniel.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUser() {
        User user = new User();
        user.setName("Daniel");
        String esperado = "Daniel";
        String real = user.getName();
        Assertions.assertEquals(esperado,real);
        assertTrue(real.equals("Daniel"));
    }

//    @Test
//    void Referencia() {
//        User user = new User(1, "Daniel", "Cabaleiro", "dcabaleiro", "prueba");
//        User user1 = new User(1, "Daniel", "Cabaleiro", "dcabaleiro", "prueba");
//        assertEquals(user,user1);
//    }


}