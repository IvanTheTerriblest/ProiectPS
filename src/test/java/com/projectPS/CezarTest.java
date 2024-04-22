package com.projectPS;

import com.projectPS.testing.Cezar;
import com.projectPS.testing.PassType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CezarTest {
    private Cezar password = new Cezar();
    @Test
    public void cezarCodeWeak1(){
       assertEquals("bcd",password.cryptPassoword(PassType.WEAK,"abc"));
    }
    @Test
    public void cezarCodeWeak2(){
        assertEquals("Bob",password.cryptPassoword(PassType.WEAK,"Ana"));
    }
    @Test
    public void cezarCodeMedium1(){
        assertEquals("EFG",password.cryptPassoword(PassType.MEDIUM,"ABC"));
    }
    @Test
    public void cezarCodeMedium2(){
        assertEquals("NEZE",password.cryptPassoword(PassType.MEDIUM,"JAVA"));
    }

    @Test
    public void cezarCodeMedium3(){
        assertEquals("bcd",password.cryptPassoword(PassType.MEDIUM,"xyz"));
    }


}
