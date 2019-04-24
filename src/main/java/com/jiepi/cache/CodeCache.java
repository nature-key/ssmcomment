package com.jiepi.cache;

import java.util.HashMap;
import java.util.Map;

public class CodeCache {


    private static CodeCache instance;

    private Map<Long, String> codeMap;

    public CodeCache() {
        this.codeMap = new HashMap<>();
    }

    public static CodeCache getInstance() {
        if (instance == null) {
            synchronized (CodeCache.class) {
                if (instance == null) {
                    instance = new CodeCache();
                }
            }
        }
        return instance;
    }

    public Boolean save(Long phone, String code) {
        if (codeMap.containsKey(phone)) {
            return false;
        }
        codeMap.put(phone,code);
        return true;
    }

    public String getCode(Long phone){
        return  codeMap.get(phone);

    }

}
