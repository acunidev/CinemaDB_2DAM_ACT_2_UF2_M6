package dam2.m6.uf2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

class UsageLombokTesting {

  @Test
  public void testGetterSettersFromUser() {
    User user = new User();
    user.setName("alex");
    user.setAge(25);
    assertEquals(user.getName(), "alex");
    assertEquals(user.getAge(), 25);
  }

  @Getter
  @Setter
  class User {

    private int age;
    private String name;
  }
}