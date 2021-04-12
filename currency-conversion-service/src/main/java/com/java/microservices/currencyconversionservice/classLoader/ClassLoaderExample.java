package com.java.microservices.currencyconversionservice.classLoader;

import com.sun.javafx.util.Logging;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.util.ArrayList;

public class ClassLoaderExample {
    public static void main(String args[]) {
        //loading java classes at run time dynamically to the jvm

        System.out.println("Class of loader of this class" + ClassLoaderExample.class.getClassLoader()); // application class loader(AppClassLoader)

        System.out.println("Class loader of logging " + Logging.class.getClassLoader()); //ExtClassLoader

        System.out.println("Class loader of array list" + ArrayList.class.getClassLoader()); //BootstrapClassLoader(displayed as null)

        System.out.println("Class loader of spring" + SpringApplication.class.getClassLoader());

        System.out.println("Class loader of array list" + Integer.class.getClassLoader());
        //App class loader loads our own files in the classpath

        //ExtClassLoader loads classes that are an extension of standard core java classes

        //Bootstarp class loader is the parent of all others. it is showing null becoz the bootstrap class loader written in native language
        //Bootstarp class loader loads java classes.  java.lang.ClassLoader

        //The class loaders are also classes it self so who will load these classes

        //This is where bootstrap or primordial class loader comes into picture. it is mainly responsible for loading internal java classes
        //This bootstrap class loader is part of the core jvm and written in native language

        //Different platforms have different implementations of this class loader
    }
}
