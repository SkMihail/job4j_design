package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte by = Byte.MAX_VALUE;
        short sh = Short.MAX_VALUE;
        int num = Integer.MAX_VALUE;
        char ch = Character.MAX_VALUE;
        boolean bool = true;
        float fl = Float.MAX_VALUE;
        double db = Double.MAX_VALUE;
        String str = "some value";

        LOG.debug("use different variables: byte={} short={} int={} char={}", by, sh, num, ch);
        LOG.warn("use different variables: boolean={} float={} double={} String={}", bool, fl, db, str);
    }
}
