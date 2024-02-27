package custom;

import utility.Utility;

public class SingletonClass {
	private static SingletonClass instance;
	
	private SingletonClass() {}
	
	public static SingletonClass getInstance() {
		if(Utility.isNull(instance)) {
			synchronized (SingletonClass.class) {
				if(Utility.isNull(instance)) {
					instance = new SingletonClass();
				}
			}
		}
		return instance;
	}
}

/*
 * Eager Initialization
 * Static block Initialization
 * Lazy Initialization
 * Thread Safe Singleton
 * Double-Checked Locking Singleton
 * Bill Push Singleton
 * Enum Singleton
 * 
 * Using Reflections can destroy singleton
 * Serialization also revokes singleton -> avoided by overriding readResolve() method to return the instance
 * 
 * Cloning can cause multiple instance -> avoided by overriding protected Object clone() to throw exception
 * Multiple class loaders calling this instance at a time will create multiple instances
 * */