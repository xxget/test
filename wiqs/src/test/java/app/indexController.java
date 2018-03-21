package app;

import org.junit.Test;

import com.lding.wiqs.modular.common.controller.IndexController;

public class indexController {
	
	public static void main(String[] args) {
		IndexController indexController = new IndexController();
		//System.out.println(indexController.getPointInfo());
		
		System.out.println(indexController.appDict("pointType"));
	}
	
}
