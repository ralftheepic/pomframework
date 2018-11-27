package com.techm.att.gammacm.base;

public interface IConstants {
	
	public static final long DEFAULT_EXPLICIT_WAIT_TIME = 40;
	public static final long DEFAULT_IMPLICIT_WAIT_TIME = 10;


	default void child(){
		System.out.println("Giving kt to nightmare");
	}
	
	public static void parent(){
		System.out.println("Kt given by vinay");
	}
	

}
