package org.spring.springboot.Utile;

public class HealthyUtile {

	public static void main(String[] args) {
		System.out.println(getHealthyInfo("177","65","66","88","22"));

	}
	public static String getHealthyInfo(String readheight, String readweight,String readyaowei, String readtunwei, String readxuetang){
        double weight=Double.parseDouble(readweight);
        double height=Double.parseDouble(readheight);
        double yaowei=Double.parseDouble(readyaowei);
        double tunwei=Double.parseDouble(readtunwei);
        double xuetang=Double.parseDouble(readxuetang);
        double bmi=(double) ((weight/height)/height)*10000;
        double yao=(double) (yaowei/height)*100;
        double tun=(double) (tunwei/height)*100;
        System.out.println(bmi);
        StringBuffer str = new StringBuffer();
        if(bmi<18.5) {
        	str =  str.append("{\"bmi\":\""+String.format("%.2f", bmi)+"\",\"desc\":\"您的身体体重偏瘦\"");
        }else if(bmi>18.5&&bmi<23.9){
        	str = str.append("{\"bmi\":\""+String.format("%.2f", bmi)+"\",\"desc\":\"您的身体体重正常\"");
        }else if(bmi>24&&bmi<27.9){
        	str =  str.append("{\"bmi\":\""+String.format("%.2f", bmi)+"\",\"desc\":\"您的身体体重偏胖\"");
        }else if(bmi>28){
        	str =  str.append("{\"bmi\":\""+String.format("%.2f", bmi)+"\",\"desc\":\"您的身体体重重度肥胖\"");
        }
        if(yao<37) {
        	str =  str.append(",\"yao\":\"腰力纤细\"");
        }else if(yao>37&&yao<43){
        	str = str.append(",\"yao\":\"腰身完美\"");
        }else if(yao>43){
        	str =  str.append(",\"yao\":\"腰围圆滚滚\"");
        }
        if(tun<52) {
        	str =  str.append(",\"tun\":\"臀部不明显\"");
        }else if(tun>52&&tun<60){
        	str = str.append(",\"tun\":\"臀部完美\"");
        }else if(tun>60){
        	str =  str.append(",\"tun\":\"臀部过大，注意减肥\"");
        }
        if(xuetang<3.9) {
        	str =  str.append(",\"xuetang\":\"血糖过低\"}");
        }else if(xuetang>3.9&&xuetang<6.1){
        	str = str.append(",\"xuetang\":\"血糖正常\"}");
        }else if(xuetang>6.1){
        	str =  str.append(",\"xuetang\":\"血糖过高\"}");
        }
        return str.toString();
        
        
        

	}
}
