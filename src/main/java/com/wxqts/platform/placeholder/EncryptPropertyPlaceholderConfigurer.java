package com.wxqts.platform.placeholder;
/**
* @author zhoulong  E-mail:zhoulong@163.com
* @date 2018年4月18日
*/
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {	
	private String[] encryptPropNames ={"username","password"};
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {		
		if(isEncryptProp(propertyName)){
			String decryptValue = DesUtils.getDecryptString(propertyValue);
//			System.out.println(decryptValue);
			return decryptValue;
		}else{
			return propertyValue;
		}
	}
	
	/**
	 * 判断是否是加密的属性
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptProp(String propertyName){
		for(String encryptPropName:encryptPropNames){
			if(encryptPropName.equals(propertyName)){
				return true;
			}
		}
		return false;
	}
}