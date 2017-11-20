/*package cn.leadeon.utils.superdiamond;

import com.github.diamond.client.event.ConfigurationEvent;
import com.github.diamond.client.event.ConfigurationListener;

public class MyConfigurationListener implements ConfigurationListener{

	@Override
	public void configurationChanged(ConfigurationEvent event) {
		//System.out.println("配置中心发生变更，source:"+event.getSource().toString());
		//System.out.println("配置中心发生变更，type:"+event.getType());
		//System.out.println("配置中心发生变更，value："+event.getPropertyValue());
	
		System.out.println("配置中心发生变更，name："+event.getPropertyName());
		if(null!=event.getPropertyName() && "pubProvinces".equals(event.getPropertyName())){
			System.out.println("pubProvinces变更前:"+PubProvinceConfiguration.PROVICES.getPubProvinces());
			PubProvinceConfiguration.PROVICES.setPubProvinces(String.valueOf(event.getPropertyValue()));
			System.out.println("pubProvinces变更后:"+PubProvinceConfiguration.PROVICES.getPubProvinces());
		}
		
		if(null!=event.getPropertyName() && "merchant".equals(event.getPropertyName())){
			System.out.println("merchant变更前:"+MerchantConfiguration.getMerchantMap());
			MerchantConfiguration.intMap(String.valueOf(event.getPropertyValue()));
			System.out.println("merchant变更后:"+MerchantConfiguration.getMerchantMap());
		}
		
		if(null!=event.getPropertyName() && "vflowPubProvinces".equals(event.getPropertyName())){
			System.out.println("vflowPubProvinces变更前:"+VflowPubProvinceConfiguration.PROVICES.getPubProvinces());
			VflowPubProvinceConfiguration.PROVICES.setPubProvinces(String.valueOf(event.getPropertyValue()));
			System.out.println("vflowPubProvinces变更后:"+VflowPubProvinceConfiguration.PROVICES.getPubProvinces());
		}
	}

	
}
*/