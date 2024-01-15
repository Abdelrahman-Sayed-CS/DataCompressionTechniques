package LZWTechnique;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class LZW{
    private Map<String, Integer> DictionaryForCompress;
    private Map<Integer, String> DictionaryForDeCompress;
    private ArrayList<Integer> compressData;
    private String text;
    private String decompressData;
    public LZW(){
    }
    // put All character in the dictionary for compress
    public void setAllCharForCompress(){
         for (int i = 0; i <= 127; i++) {
            String key = "";
            key += (char)i;
            DictionaryForCompress.put(key, i);
        }
    }
    
     // put All character in the dictionary for compress
    public void setAllCharForDeCompress(){
         for (int i = 0; i <= 127; i++) {
            String key = "";
            key += (char)i;
            DictionaryForDeCompress.put(i, key);
        }
    }
    public ArrayList<Integer> compress(String t)
    {
        text = t;
        DictionaryForCompress = new HashMap<String, Integer>();
        compressData = new ArrayList<Integer>();
        setAllCharForCompress();
        int j = 0, valueForKey = 128;
        String str = "";
        str += text.charAt(j);
        j++;
        while( j < text.length()){
            while (DictionaryForCompress.get(str) != null && j < text.length()) {
                str += text.charAt(j);
                j++;
            }
            if(j >= text.length()){
                break;
            }
            DictionaryForCompress.put(str, valueForKey);
            compressData.add(DictionaryForCompress.get(str.substring(0, str.length()-1)));
            str = "";
            str +=  text.charAt(j-1);
            valueForKey++;
        }
        if(DictionaryForCompress.get(str) != null){
            compressData.add(DictionaryForCompress.get(str.substring(0, str.length())));
        }else{
            DictionaryForCompress.put(str, valueForKey);
            compressData.add(DictionaryForCompress.get(str.substring(0, str.length()-1)));
            compressData.add(DictionaryForCompress.get(str.substring(str.length()-1)));
        }
        if(compressData.get(compressData.size()-1) == null)
        compressData.remove(compressData.size()-1);
        return compressData;
    }
    public String decompress(ArrayList<Integer> compressedData) {
        DictionaryForDeCompress = new HashMap<Integer, String>();
        compressData = new ArrayList<Integer>();
        compressData = compressedData;
        setAllCharForDeCompress();
        decompressData = "";
        int j = 0, valueForKey = 128;
        
        decompressData += DictionaryForDeCompress.get(compressData.get(j));
        for (j = 1; j < compressData.size(); j++) {
            String key = "", curCompressInt = "";
            key = DictionaryForDeCompress.get(compressData.get(j-1));
            if(DictionaryForDeCompress.get(compressData.get(j)) != null){
                curCompressInt = DictionaryForDeCompress.get(compressData.get(j));
                curCompressInt = curCompressInt.substring(0, 1);
            }else{
                curCompressInt = key.substring(0, 1);
            }
            key+=curCompressInt;
            DictionaryForDeCompress.put(valueForKey, key);
            String value = "";
            value = DictionaryForDeCompress.get(compressData.get(j));
            decompressData += value;
            valueForKey++;
        }
        return decompressData;
    }   
}
