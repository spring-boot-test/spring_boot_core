package com.hubert.core.logging.logback;


import java.io.IOException;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Customized PatternLayoutEncoder for logback.
 * 
 * @author JackDou
 * @version 2016-8-8
 */
public class LogBackExEncoder extends PatternLayoutEncoder {
    static {
        PatternLayout.defaultConverterMap.put("TID", ThreadNumConverter.class.getName());
        PatternLayout.defaultConverterMap.put("threadNum", ThreadNumConverter.class.getName());
    }
    @Override
    public void doEncode(ILoggingEvent event) throws IOException {
        super.doEncode(event);
    }
}
