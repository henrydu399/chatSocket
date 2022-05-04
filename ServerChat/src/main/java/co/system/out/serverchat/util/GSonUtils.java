
package co.system.out.serverchat.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSonUtils {

    /**
     * Convierte un objeto a json
     *
     * @param src
     * @return
     */
    public static String serialize(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }
    
    public static String serializePretty(Object src) {
    	 Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(src);
    }

    /**
     * Convierte un objeto a otro objeto
     *
     * @param src
     * @param dClass
     * @param <D>
     * @return
     */
    public static <D> D toObject(Object src, Class<D> dClass) {
        Gson gson = new Gson();
        String srcJson = gson.toJson(src);
        return gson.fromJson(srcJson, dClass);
    }

    /**
     * Convierte un objeto a otro objeto
     *
     * @param srcJson
     * @param dClass
     * @param <D>
     * @return
     */
    public static <D> D toObject(String srcJson, Class<D> dClass) {
        Gson gson = new Gson();
        return gson.fromJson(srcJson, dClass);
    }
    
   
	

    

}