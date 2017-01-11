package com.hubert.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件加载与读写工具类
 * 
 * @author JackDou
 * @since 2016.5.2
 */
public class IniUtil {

    /* properties object */
    private Properties proper = null;

    /* log message info */
    private String logMsg = "";

    /* properties file full path */
    private String iniFile = "";

    /**
     * load a iniFile from store.
     * 
     * @param iniFile
     *            file name of iniFile(e.g: D:\test1.properties)
     * @return Propertiest instance
     * @throws IOException
     *             any IO exception will be throw out
     */
    public Properties loadFileFromStore(String iniFile) throws IOException {
        this.iniFile = iniFile;
        File file = new File(iniFile);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
        return loadFileFromInputStream(isr);
    }

    /**
     * load a iniFile from class path.
     * 
     * @param iniFile
     *            file name of iniFile(e.g: com/jl/config/test1.properties)
     * @return Propertiest instance
     * @throws IOException
     *             any IO exception will be throw out
     */
    public Properties loadFileFromClassPath(String iniFile) throws IOException {
        ClassLoader classLoader = IniUtil.class.getClassLoader();
        URL url = classLoader.getResource(iniFile);
        InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
        return loadFileFromInputStream(isr);
    }

    /**
     * get all keys from properties file
     * 
     * @return List object with String value(s)
     */
    public List<String> getAllKeys() {

        List<String> list = new ArrayList<String>();

        if (proper == null) {
            System.out
                    .println("[WARN]Method IniUtil::List<String> getAllKeys() "
                            + "the properties file is not init.");
            return list;
        }

        Enumeration<?> enums = proper.propertyNames();

        String key = "";
        while (enums.hasMoreElements()) {
            key = (String) enums.nextElement();
            list.add(key);
        }

        return list;
    }

    /**
     * get all values from properties file
     * 
     * @return List object with String value(s)
     */
    public List<String> getAllValues() {

        List<String> list = new ArrayList<String>();

        if (proper == null) {
            System.out
                    .println("[WARN]Method IniUtil::List<String> getAllValues() "
                            + "the properties file is not init.");
            return list;
        }

        Enumeration<?> enums = proper.propertyNames();

        String key = "";
        String value = "";
        while (enums.hasMoreElements()) {
            key = (String) enums.nextElement();
            value = getValue(key, "");
            list.add(value);
        }

        return list;
    }

    /**
     * get key(s) and value(s) from Properties file
     * 
     * @return HashMap object, key is the prop key and value is prop value. all
     *         of them are String type
     */
    public Map<String, String> getAllKeyValues() {

        Map<String, String> map = new HashMap<String, String>();

        if (proper == null) {
            System.out
                    .println("[WARN]Method IniUtil::Map<String, String> getAllKeyValues() "
                            + "the properties file is not init.");
            return map;
        }

        Enumeration<?> enums = proper.propertyNames();
        String key = "";
        String value = "";
        while (enums.hasMoreElements()) {
            key = (String) enums.nextElement();
            value = getValue(key, "");
            map.put(key, value);
        }

        return map;
    }

    /**
     * get the prop value by key
     * 
     * @param key
     *            key name
     * @return prop value, if key not exist will return null
     */
    public String getValue(String key) {
        return getValue(key, null);
    }

    /**
     * get the prop value by key
     * 
     * @param key
     *            key name
     * @param defValue
     *            defalut value
     * @return prop value, if key not exist will return defvalue
     */
    public String getValue(String key, String defValue) {
        String strRet = defValue;
        if (key != null && (!key.equals(""))) {
            strRet = proper.getProperty(key, defValue);
        }
        return strRet;
    }

    /**
     * set the prop key and value
     * 
     * @param key
     *            key name
     * @param value
     *            value string
     * @return the previous value of the specified key in this property list, or
     *         null if it did not have one.
     */
    public Object setValue(String key, String value) {
        return proper.setProperty(key, value);
    }

    /**
     * save the prop key(s) and value(s) to the default properties file
     * 
     * @param comment
     *            prop comment
     * @return if save successfully it will return true otherwise will reutrn
     *         false
     * @throws IOException
     *             any IO about exception will be throw out
     */
    public boolean persist(String comment) throws IOException {
        boolean bRet = true;
        if (proper == null) {
            logMsg = "Properties object met null pointer";
            throw new IOException(logMsg);
        }

        OutputStream out = null;
        out = new FileOutputStream(iniFile);

        try {
            proper.store(out, comment);
        } catch (IOException e) {
            logMsg = "Write to file:'" + iniFile + "' failed!";
            throw new IOException(logMsg);
        } finally {
            out.close();
        }
        return bRet;
    }

    /**
     * clear the properties instanse.
     */
    public void clear() {
        if (proper != null) {
            proper.clear();
        }
    }

    private Properties loadFileFromInputStream(InputStreamReader inputStream)
            throws IOException {
        try {
            if (proper == null) {
                proper = new Properties();
            }
            proper.load(inputStream);
        } catch (IOException e) {
            throw new IOException(
                    "Loading file met IOException from input stream.");
        } finally {
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
        }
        return proper;
    }
}
