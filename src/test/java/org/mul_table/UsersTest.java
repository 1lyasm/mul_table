package org.mul_table;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class UsersTest {
    @Test
    void test_get_user_by_uname_passw() {
        Users users = new Users();
        ArrayList<User> user_array = new ArrayList<>();
        user_array.add(new User("ilyas", "123"));
        user_array.add(new User("wesley", "234"));
        users.set_user_array(user_array);
        User found_user = users.get_user_by_uname_passw("wesley", "234");
        Assertions.assertTrue(found_user.get_username().equals("wesley"));
        Assertions.assertTrue(found_user.get_passw().equals("234"));
        found_user = users.get_user_by_uname_passw("ding", "123");
        Assertions.assertEquals(found_user, null);
        found_user = users.get_user_by_uname_passw("ilyas", "124");
        Assertions.assertEquals(found_user, null);
    }

    @Test
    void test_username_exists() {
        Users users = new Users();
        ArrayList<User> user_array = new ArrayList<>();
        user_array.add(new User("ilyas", "123"));
        user_array.add(new User("wesley", "234"));
        users.set_user_array(user_array);
        Assertions.assertTrue(users.username_exists("ilyas"));
        Assertions.assertTrue(users.username_exists("wesley"));

        Assertions.assertFalse(users.username_exists("moo"));
    }

    @Test
    void test_user_exists() {
        Users users = new Users();
        ArrayList<User> user_array = new ArrayList<>();
        user_array.add(new User("ilyas", "123"));
        user_array.add(new User("wesley", "234"));
        users.set_user_array(user_array);
        Assertions.assertEquals(true, users.user_exists("wesley", "234"));
        Assertions.assertEquals(true, users.user_exists("ilyas", "123"));

        Assertions.assertEquals(false, users.user_exists("ilyas", "124"));
        Assertions.assertEquals(false, users.user_exists("ilyaa", "123"));
        Assertions.assertEquals(false, users.user_exists("wesley", "123"));
    }
}