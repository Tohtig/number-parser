package numberparser.number.invertmaps;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Properties;

/**
 *	Фабрика по созданию коннекторов к словарям. По принципу синглетона. </br>
 *	<b>TODO</b> Продумать возможность прикрутить словари для разных локалей.
 */
public class InvertMapSelector {
	
    private static InvertMapSelector instance;
    /** Карта экземпляров коннекторов к properties со словарями.*/
    private EnumMap<InvertMapType, Properties> mapIMap;
    /** Карта путей до properties со словарями.*/
    private EnumMap<InvertMapType, String> mapIMapPath;
	private Properties iMap;
	private InputStream in;
	private InputStreamReader inR;
    
    public static InvertMapSelector getInstance() {
    	
        if (instance==null) {
            instance = new InvertMapSelector();
        }
        return instance;
    }
    
    private InvertMapSelector() {
    	
    	mapIMap = new EnumMap<>(InvertMapType.class);
    	mapIMapPath = new EnumMap<>(InvertMapType.class);
 
//      Использовать ли отдельный бин-свойств?
    	mapIMapPath.put(InvertMapType.DIGITAL_EXPONENTA, 
    			"/DigitExponenta.properties");
    	mapIMapPath.put(InvertMapType.DIGITAL_MANTISSA, 
    			"/DigitMantissa.properties");
    	mapIMapPath.put(InvertMapType.DIGITAL_MANTISSA_EXCEPTIONS, 
    			"/DigitMantissaEx.properties");
    	mapIMapPath.put(InvertMapType.PHRASE_EXPONENTA, 
    			"/PhraseExponenta.properties");
    	mapIMapPath.put(InvertMapType.PHRASE_MANTISSA, 
    			"/PhraseMantissa.properties");
    }
	
    /** Фабричный метод возвращающий требуемый словарь */ 
	public Properties getInvertMap(InvertMapType mapType){
		
		Properties iMap = null;
        switch (mapType) {
            case DIGITAL_EXPONENTA:
            	iMap = getMap(InvertMapType.DIGITAL_EXPONENTA);
                break;
            case DIGITAL_MANTISSA:
            	iMap = getMap(InvertMapType.DIGITAL_MANTISSA);
                break;
            case DIGITAL_MANTISSA_EXCEPTIONS:
            	iMap = getMap(InvertMapType.DIGITAL_MANTISSA_EXCEPTIONS);
                break;
            case PHRASE_EXPONENTA:
            	iMap = getMap(InvertMapType.PHRASE_EXPONENTA);
            	break;
            case PHRASE_MANTISSA:
            	iMap = getMap(InvertMapType.PHRASE_MANTISSA);
            	break;
        }
        return iMap;
	}
	
	private Properties getMap(InvertMapType mapType){
		
		if (mapIMap.containsKey(mapType)) {
			return mapIMap.get(mapType);
        } else{
        	mapIMap.put(mapType, createConnection(mapType));
        	return mapIMap.get(mapType);
        }
	}
	
	private Properties createConnection(InvertMapType mapType){
		
		try {
			in = InvertMapSelector.class
					.getResourceAsStream(mapIMapPath.get(mapType));
			inR = new InputStreamReader(in, "UTF-8");
//	будь плеяда Exceptions больше, обернул бы в собственный ResourceException
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		iMap = new Properties();
		try {
			iMap.load(inR);
			inR.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return iMap;
	}
}
