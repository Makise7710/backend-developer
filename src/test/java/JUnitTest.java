import org.junit.jupiter.api.*;


public class JUnitTest {
    @Test
    @DisplayName("1+2는 3")
    public void junitTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum,  a+b);
    }

    public void junitFailTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        System.out.println("1+3는 3이다");
        Assertions.assertEquals(sum, a+b);
    }

    @BeforeAll
    public void prepareAll(){
        System.out.println("테스트 준비");
    }

    @AfterAll
    public void cleanAll(){
        System.out.println("nihao");
    }
}