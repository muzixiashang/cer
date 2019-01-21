package com.liyunet.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class ImgUploadUtil {

	public static void main(String[] args) {
		String str = "data:image/png;base64sdfsfsdf";
		
		System.out.println(str.indexOf(";base64"));
		System.out.println(str.substring(str.indexOf(";base64") + 7));
	}
	
	
	public static ImgData decodeImg(String data){
		
		Base64 base64 = new Base64();
		
		
		String suffixName = StringUtils.substringBetween(data, "data:image/", ";base64");
		
		byte[] k = base64.decode(data.substring(data.indexOf(";base64,") + 7));  
        InputStream is = new ByteArrayInputStream(k);  
        String prefixName = UUID.randomUUID().toString();  
		
        //图片压缩
        /* double ratio = 1.0;  
        BufferedImage image = ImageIO.read(is);  
        int newWidth = (int) (image.getWidth() * ratio);  
        int newHeight = (int) (image.getHeight() * ratio);  
        Image newimage = image.getScaledInstance(newWidth, newHeight,  
        Image.SCALE_SMOOTH);  
        BufferedImage tag = new BufferedImage(newWidth, newHeight,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics g = tag.getGraphics();  
        g.drawImage(newimage, 0, 0, null);  
        g.dispose();  
        ImageIO.write(tag, "jpg", new File(imgFilePath)); */
        
        return new ImgData(prefixName,suffixName,is);
        
	}
	
	
	
	public static class ImgData{
		public ImgData(){}
		public ImgData(String prefixName,InputStream is){
			this.is = is;
			this.prefixName = prefixName;
		}
		public ImgData(String prefixName,String suffixName , InputStream is){
			this.is = is;
			this.prefixName = prefixName;
			this.suffixName = suffixName;
		}
		
		private String prefixName;
		private String suffixName;
		
		
		public void saveToServer(String serverPath) throws IOException{
			
			File basePath = new File(serverPath);
			if(!basePath.exists())
				basePath.mkdirs();
			
			File dest = new File(basePath,this.getFullName());
			FileUtils.copyInputStreamToFile(this.getInputStream(), dest);
			
		}
		
		
		public String getPrefixName() {
			return prefixName;
		}
		public void setPrefixName(String prefixName) {
			this.prefixName = prefixName;
		}
		public String getSuffixName() {
			return suffixName;
		}
		public void setSuffixName(String suffixName) {
			this.suffixName = suffixName;
		}
		public String getFullName() {
			return prefixName + "." +suffixName;
		}
		
		private InputStream is;
		
		public InputStream getInputStream(){
			return is;
		}
		
		public void setInputStream(InputStream is){
			this.is = is;
		}
	}
	
}
