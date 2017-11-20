/*package cn.leadeon.utils.superdiamond;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.github.diamond.client.PropertiesConfiguration;
import com.github.diamond.client.PropertiesConfigurationFactoryBean;


public enum PubProvinceConfiguration {
	
	PROVICES("");
	
	private PubProvinceConfiguration(String pubProvinces) {
		PropertiesConfiguration prop = PropertiesConfigurationFactoryBean.getPropertiesConfiguration();
		if(StringUtils.isNotEmpty(prop.getString("pubProvinces"))){
			this.pubProvinces = prop.getString("pubProvinces");
		}else{
			this.pubProvinces = "";
		}
	}
	
	private String pubProvinces;
	
	public String getPubProvinces() {
		return pubProvinces;
	}

	public void setPubProvinces(String pubProvinces) {
		this.pubProvinces = pubProvinces;
	}
	
	public static List<String> getPubProvinceList(){
		if(null != PROVICES.getPubProvinces() && !"".equals(PROVICES.getPubProvinces())){
			String[] arrs = PROVICES.getPubProvinces().split(",");
			if(null != arrs && arrs.length > 0){
				return Arrays.asList(arrs);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public static boolean isPub(String provCode){
		if(StringUtils.isNotEmpty(provCode) && provCode.length()==3){
			String pubProvices = PROVICES.getPubProvinces();
			return (pubProvices.indexOf(provCode) > -1);
		}
		return false;
	}
	
	
}
*/