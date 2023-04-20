package de.htwberlin.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import de.htwberlin.intro.SimpleService;

public class SimpleTest {
  SimpleService s;

  @org.junit.Before
  public void setUp() throws Exception {
    s = new SimpleService();
  }

  @org.junit.Test
  public void test1() {
    assertEquals("hw", s.hw());
  }

  @org.junit.Test(expected = RuntimeException.class)
  public void test2() {
    assertEquals("exe", s.exe(1));
  }

  @org.junit.Test()
  public void test3() {
    int[] a1 = new int[] { 1, 2, 3, 4};
    int[] a2 = new int[] { 1, 2, 3, 4 };
    assertArrayEquals(a1, a2);
  }
}
