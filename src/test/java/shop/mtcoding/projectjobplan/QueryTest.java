package shop.mtcoding.projectjobplan;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class QueryTest {

    @Test
    public void hello(){
        List<String> names = Arrays.asList("java", "spring");

        String q = "select * from board_tb where name in (";

        int size = names.size();;
        for (int i=0; i<size; i++){
            if(i == size-1){
                System.out.println("마지막바퀴");
                q = q + "'spring')";
            }else{
                System.out.println("마지막바퀴가 아닙니다");
                q = q + "'java',";
            }
            System.out.println(q);
        }
    }

}
