package com.example.demo.Controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.springframework.web.multipart.MultipartFile;
//视频解析包
//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.MultimediaInfo;





public class FileUtil {
	//用于判断附件是否为图片
	private final static String PREFIX_IMAGE="image/";
	//用于判断附件是否为视频
	private final static String PREFIX_VIDEO="video/";
	
	public static void main(String[] args) {
//		String dirName = "d:/FH/topic/";// 创建目录
//		FileUtil.createDir(dirName);
//		getVideoTime("E:\\Desktop\\安监系统视频\\安全生产事故-省级\\安全生产事故-省级.wmv");
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 读取到字节数组0
	 * 
	 * @param filePath //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			//System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** 
	* @Title: isImage 
	* @Description: 判断文件是否为图片,是，返回true,反之,返回false
	* @param f
	* @return
	* @author 陈君平 ChenJunPing
	* @date 2019年10月25日 下午5:39:55
	*/
	public static boolean isImage(MultipartFile f) {
		// TODO Auto-generated method stub
		String fileType = f.getContentType();
		if(fileType!=null && fileType.contains(PREFIX_IMAGE)){
			return true;
		}
		return false;
	}
	public static boolean isImage(String fileType) {
		// TODO Auto-generated method stub
		if(fileType!=null && fileType.contains(PREFIX_IMAGE)){
			return true;
		}
		return false;
	}
	/** 
	* @Title: isVideo
	* @Description: 判断文件是否为视频,是，返回true,反之,返回false
	* @param f
	* @return
	* @author 陈君平 ChenJunPing
	* @date 2019年10月25日 下午5:39:55
	*/
	public static boolean isVideo(MultipartFile f) {
		// TODO Auto-generated method stub
		String fileType = f.getContentType();
		if(fileType!=null && fileType.contains(PREFIX_VIDEO)){
			return true;
		}
		return false;
	}
	public static boolean isVideo(String fileType) {
		// TODO Auto-generated method stub
		if(fileType!=null && fileType.contains(PREFIX_VIDEO)){
			return true;
		}
		return false;
	}
	/** 
	* @Title: getVideoTime 
	* @Description: 获取视频时长
	* @param f
	* @return
	* @author 陈君平 ChenJunPing
	* @date 2019年10月18日 下午3:03:17
	*/
	/*public static String getVideoTime(File f) {
        String length = null;
        try {
        	Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfo(f);
            long ls = m.getDuration()/1000;//秒
            int hour = (int) (ls/3600);//小时
            int minute = (int) (ls%3600)/60;//分钟
            int second = (int) (ls-hour*3600-minute*60);//秒
            if(hour==0){
          	  length = fillZero(minute)+":"+fillZero(second);
            }else{
          	  length = fillZero(hour)+":"+fillZero(minute)+":"+fillZero(second);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("视频时长为："+length);
        return length;
    }*/
	/** 
	* @Title: getVideoTime 
	* @Description: 获取视频时长
	* @param fURL 文件的绝对路径
	* @return
	* @author 陈君平 ChenJunPing
	* @date 2019年10月18日 下午3:04:49
	*/
	/*public static String getVideoTime(String fURL) {
      return getVideoTime(new File(fURL));
	}*/
	/** 
	* @Title: fillZero 
	* @Description: 补零
	* @param i
	* @return
	* @author 陈君平 ChenJunPing
	* @date 2019年10月25日 下午6:34:49
	*/
	private static String fillZero(int i) {
		String v = null;
		if(i>=0 && i<10){
			v ="0"+i;
		}else{
			v =i+"";
		}
		return v;
	}
}