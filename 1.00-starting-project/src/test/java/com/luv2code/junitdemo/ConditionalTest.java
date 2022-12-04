package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("Don't run until JIRA #123 is resolved")
    void basicTest(){

    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly(){
        System.out.println("testForWindowsOnly");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly(){
        System.out.println("testForLinuxOnly");
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    void testForWindowsAndMacOnly(){
        System.out.println("testForWindowsAndMacOnly");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testOnlyForJava17(){
        System.out.println("testOnlyForJava17");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void testOnlyForJava13(){
        System.out.println("testOnlyForJava13");
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_13, max = JRE.JAVA_17)
    void testOnlyForJavaRange(){
        System.out.println("testOnlyForJavaRange");
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_13)
    void testOnlyForJavaRangeMin(){
        System.out.println("testOnlyForJavaRangeMin");
    }

    @Test
    @EnabledIfSystemProperty(named = "LUV2CODE_SYS_PROP", matches = "CI_CD_DEPLOY")
    void testOnlyForSystemProperty(){
        System.out.println("testOnlyForSystemProperty");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "LUV2CODE_ENV", matches = "DEV")
    void testOnlyForDevEnvironment(){
        System.out.println("testOnlyForDevEnvironment");
    }

}
