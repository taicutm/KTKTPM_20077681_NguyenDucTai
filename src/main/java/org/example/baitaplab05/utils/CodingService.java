package org.example.baitaplab05.utils;

import java.util.Base64;

public class CodingService {
    // viết 2 hàm encode và decode
    public String encode(String text)
    {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    public String decode(String text) {
         byte[] decodeBytes = Base64.getDecoder().decode(text) ;
        return new String(decodeBytes);
    }
}
