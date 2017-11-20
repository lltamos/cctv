/*package cn.leadeon.utils.superdiamond;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.diamond.client.PropertiesConfiguration;
import com.github.diamond.client.PropertiesConfigurationFactoryBean;

public class MerchantConfiguration {
	
	private MerchantConfiguration(){}

	private static final Map<String,String> merchantMap = new HashMap<String,String>();
	
	private static final Map<String,String> merchant2provMap = new HashMap<String,String>();

	static{
		PropertiesConfiguration prop = PropertiesConfigurationFactoryBean.getPropertiesConfiguration();
		if(StringUtils.isNotEmpty(prop.getString("merchant"))){
			String merchants = prop.getString("merchant");
			intMap(merchants);
		}
	}
	
	public synchronized static void intMap(String merchants){
		if(merchantMap!=null){
			merchantMap.clear();
		}
		if(merchant2provMap!=null){
			merchant2provMap.clear();
		}
		String[] args = merchants.split(";");
		if(null!=args && args.length>0){
			String[] merchantArr = null;
			for(int i=0; i<args.length ;i++){
				merchantArr = args[i].split(",");
				if(null != merchantArr && merchantArr.length==2){
					merchantMap.put(merchantArr[0], merchantArr[1]);
					merchant2provMap.put(merchantArr[1], merchantArr[0]);
				}
			}
		}
	}
	
	public static Map<String,String> getMerchantMap(){
		return merchantMap;
	}
	
	public static String getMerchant(String provCode){
		return merchantMap.get(provCode);
	}
	
	public static String getProvince(String merchantId){
		return merchant2provMap.get(merchantId);
	}
	
//	public synchronized static void putMerchant(String provCode,String merchant){
//		merchantMap.put(provCode, merchant);
//	}
}
*/