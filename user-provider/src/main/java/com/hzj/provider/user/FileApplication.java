package com.hzj.provider.user;

import com.hzj.provider.user.controller.FileListener;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.File;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@MapperScan("com.hzj.provider.user.mapper")
public class FileApplication {

	final  static Logger logger = Logger.getLogger(FileApplication.class);
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FileApplication.class, args);
		System.out.println("*********************************启动成功*********************************");
		// 监控目录
		String rootDir = "D:\\test";
		// 轮询间隔 5 秒
		long interval = TimeUnit.SECONDS.toMillis(1);
		// 创建过滤器
		IOFileFilter directories = FileFilterUtils.and(
				FileFilterUtils.directoryFileFilter(),
				HiddenFileFilter.VISIBLE);
		IOFileFilter files       = FileFilterUtils.and(
				FileFilterUtils.fileFileFilter(),
				FileFilterUtils.suffixFileFilter(".txt"));
		IOFileFilter filter = FileFilterUtils.or(directories, files);
		// 使用过滤器
		FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
		//不使用过滤器
		//FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
		observer.addListener(new FileListener());
		//创建文件变化监听器
		FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
		// 开始监控
		try{
			monitor.start();
			System.out.println("***************监控中***************");
		}
		catch (Exception e){
			logger.error("异常处理",e);
		}
	}
}
