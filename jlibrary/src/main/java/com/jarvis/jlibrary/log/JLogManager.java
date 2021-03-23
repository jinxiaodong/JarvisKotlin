package com.jarvis.jlibrary.log;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public class JLogManager {

    private JLogConfig config;
    private  List<JLogPrinter> printers = new ArrayList<>();

    private static JLogManager instance;

    private JLogManager(JLogConfig config, JLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static JLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull JLogConfig config, JLogPrinter... printers) {
        instance = new JLogManager(config, printers);
    }

    public JLogConfig getConfig() {
        return config;
    }


    public  void addPrinter(JLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(JLogPrinter printer){
        printers.remove(printer);
    }

    public List<JLogPrinter> getPrinters() {
        return printers;
    }
}
