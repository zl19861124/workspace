package com.micro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LogTest {
    @Test
    public void test()
    {
        String path1=System.getProperty("user.dir");
        String path2=this.getClass().getResource("/").getPath();
        System.out.println(path1);
        System.out.println(path2);
        getResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }
    public static final String DEFAULT_PREFIX = "";

    public static final String DEFAULT_SUFFIX = "";

    private static final String[] SERVLET_LOCATIONS = { "/" };
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    static String[] getResourceLocations(String[] staticLocations) {
        String[] locations = new String[staticLocations.length
                + SERVLET_LOCATIONS.length];
        System.arraycopy(staticLocations, 0, locations, 0, staticLocations.length);
        System.arraycopy(SERVLET_LOCATIONS, 0, locations, staticLocations.length,
                SERVLET_LOCATIONS.length);
        for(String str:locations)
        {
            System.out.println(str);
        }
        return locations;
    }
}
