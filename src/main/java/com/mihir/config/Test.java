package com.mihir.config;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Test
{
	
	 
	
	 

	 
	    static final Logger logger = Logger.getLogger(Test.class);
	 
	    public static void main(String[] args)
	    {
	        //Configure logger
	        BasicConfigurator.configure();
	        logger.debug("Hello World!");
	    }
	

}
